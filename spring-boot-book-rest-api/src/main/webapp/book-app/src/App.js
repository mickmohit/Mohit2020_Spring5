import React from 'react';
import logo from './logo.svg';
import './App.css';
import NavigationBar from './components/NavigationBar';
import Welcome from './components/welcome';
import Footer from './components/footer';
import Booklist from './components/booklist';
import UserList from './components/UserList';
import Book from './components/book';
import {Container,Row,Jumbotron,Col} from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

import {Provider} from 'react-redux';
import store from './services/store';

function App() {
	
	const marginTop={
			marginTop:"20px"
	};
	
  return (
    <Router>
    <NavigationBar/>
   
     <Container>
     <Row>
   
    <Col lg={12} className={"margin-top"}>
    
     		<Switch>
     		<Route path="/" exact component={Welcome}/>
 			<Route path="/add" exact component={Book}/>
     		<Route path="/edit/:id" exact component={Book}/>
     		<Route path="/list" exact component={Booklist}/>
     		<Route path="/users" exact component={UserList}/>     		
     		
     		<Route path="/users" exact component={() => 
     		<Provider store={store}>
     		 
     		    <UserList />
     		  
     		</Provider>
     		}/>  
     		
     		</Switch>
     
	    	
	  </Col>
     </Row>
     </Container>
     
     <Footer/>
    
    </Router>
  );
}

export default App;
