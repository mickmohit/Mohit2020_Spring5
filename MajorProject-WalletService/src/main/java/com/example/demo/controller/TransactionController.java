package com.example.demo.controller;

import antlr.StringUtils;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Utils.TransactionValidator;
import com.example.demo.Utils.WalletValidator;
import com.example.demo.exception.TransactionBadRequest;
import com.example.demo.model.AddBalanceDetails;
import com.example.demo.model.Transaction;
import com.example.demo.model.User;
import com.example.demo.model.Wallet;
import com.example.demo.model.WalletInRedis;
import com.example.demo.repository.TransactionRepository;
import com.example.demo.repository.WalletDaoRedis;
import com.example.demo.repository.WalletRepository;
import com.example.demo.service.UserService;

import java.sql.Date;
import java.util.Calendar;

@RestController
public class TransactionController {

    @Autowired
    private WalletRepository walletRepository;
    
    @Autowired
    private TransactionRepository trepository;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    private WalletDaoRedis WDao;
    
    @Autowired
    private UserService userService;

    WalletValidator walletValidator = new WalletValidator();
    TransactionValidator validator = new TransactionValidator();
    
    private static final String TOPIC = "test";
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
    //RedissonClient redisson = Redisson.create();


    @PostMapping("/sendMoney")
    @ApiOperation(value = "To send money from one user to another ")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    Transaction addBal(@RequestBody Transaction transaction) throws Exception {

        if(!validator.validateRequest(transaction)){
            throw new TransactionBadRequest();
        }
        transaction.setDate(new Date(Calendar.getInstance().getTime().getTime()));
        User sender = userService.getAUser(String.valueOf(transaction.getSid()));
        User receiver = userService.getAUser(String.valueOf(transaction.getRid()));
        if(sender==null || receiver==null){
            logger.info("No wallet for sender or receiver");
            throw new TransactionBadRequest();
        }

        WalletInRedis redisWalletSender = WDao.getWallet(sender.getId());
        WalletInRedis redisWalletReceiver = WDao.getWallet(receiver.getId());
        Wallet senderWallet = walletRepository.findWalletByUserId(sender.getId());
        Wallet receiverWallet = walletRepository.findWalletByUserId(receiver.getId());

        int amt = transaction.getAmount();

        if (senderWallet.getBalance() < amt) {
            throw new Exception("Not Sufficient Balance");
        }
        senderWallet.setBalance(senderWallet.getBalance()-amt);
        redisWalletSender.setAmount(senderWallet.getBalance()-amt);

        receiverWallet.setBalance(receiverWallet.getBalance()+amt);
        redisWalletSender.setAmount(receiverWallet.getBalance()+amt);

        transaction.setStatus("SUCCESS");
        logger.info(String.format("$$ -> Producing Transaction --> %s", transaction));
        kafkaTemplate.send(TOPIC, Integer.toString(transaction.getId()));
        walletRepository.save(receiverWallet);
        walletRepository.save(senderWallet);
        // Updating balance in Redis Cache
        WDao.updateWallet(redisWalletReceiver);
        WDao.updateWallet(redisWalletSender);
        return trepository.save(transaction);
    }


    @GetMapping("/getBal/{id}")
    @ApiOperation(value = " To get Balance of a user ")
    int getBal(@PathVariable int id) throws Exception {
        WalletInRedis walletInRedis = null;
        walletInRedis = WDao.getWallet(id);
        // If not present in the Redis then go for MySQL
        if(walletInRedis==null){
            Wallet wallet = walletRepository.findWalletByUserId(id);

            if(wallet==null) throw new Exception("Wallet Not Found");
            else {
                return wallet.getBalance();
            }

        }else{
            return walletInRedis.getAmount();
        }
    }
    @PostMapping("/addBalance")
    @ApiOperation(value = " To Add balance in users wallet ")
    AddBalanceDetails addBalance(@RequestBody AddBalanceDetails request){

        logger.info(request.toString());
        Wallet wallet = walletRepository.findWalletByUserId(request.getUid());

        wallet.setBalance(request.getAmount()+wallet.getBalance());

        WalletInRedis walletInRedis = WDao.getWallet(request.getUid());
        walletInRedis.setAmount(request.getAmount()+wallet.getBalance());

        walletRepository.save(wallet);
        WDao.updateWallet(walletInRedis);
        return request;
    }

    @GetMapping("/txnHistory/{id}")
    @ApiOperation(value = " To create and mail a .csv file of the Transaction History ")
    String getTransactionHistory(@PathVariable int id) {
        logger.info(String.format("$$ -> Producing Transaction --> %s", id));
        String new_id = Integer.toString(id)+"txn";
        kafkaTemplate.send(TOPIC, new_id);
        return "You will get the file on your email";
    }
}