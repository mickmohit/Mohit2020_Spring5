import React from 'react';
import {Card, Form, Button, Col} from 'react-bootstrap';

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faSave, faPlusSquare,faUndo, faList,faEdit} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import {MyToast} from './MyToast';

class Book extends React.Component
{

	_isMounted = false;
	 
	constructor(props){
		super(props);
		//this.state={title:'', author:'', isbnNo:'',price:'',language:'',coverphotoURL:''};
		this.state= this.intitalState;
		this.state= {genres: []};
		this.state.show=false;
		this.submitBook=this.submitBook.bind(this);
		this.bookChange=this.bookChange.bind(this);
	}
	
	intitalState={id:'', title:'', author:'', isbnNumber:'',price:'',language:'',coverPhotoURL:'', genre:''};
	
	componentDidMount()
	{
		this._isMounted = true;
		const bookId= this.props.match.params.id;
		if(bookId)
			{
			 this.findBookById(bookId);	
			}
		this.findAllGenres();
	}
	
	componentWillUnmount() {
	    this._isMounted = false;
	  }
	 
	
	/*findBookById = (bookId) =>
	{
		axios.get("http://localhost:8081/rest/books/"+bookId)
		.then(response =>{
			if(response.data!=null)
				{
				this.setState({
					id:response.data.id, 
					title:response.data.title,
					author:response.data.author, 
					isbnNumber:response.data.isbnNumber,
					price:response.data.price,
					language:response.data.language,
					coverPhotoURL:response.data.coverPhotoURL	
				});
				}
			})
		.catch((error) =>{
			console.log("Error -"+error);
			});
	}*/
	
	findBookById = (bookId) =>
	{
		fetch("http://localhost:8081/rest/books/"+bookId)
		.then(response => response.json())
		.then(book =>{
			if(book!=null)
				{
				this.setState({
					id:book.id, 
					title:book.title,
					author:book.author, 
					isbnNumber:book.isbnNumber,
					price:book.price,
					language:book.language,
					coverPhotoURL:book.coverPhotoURL,	
					genre: book.genre
				});
				}
			})
		.catch((error) =>{
			console.log("Error -"+error);
			});
	}
	
	findAllGenres = () =>
	{
		axios.get("http://localhost:8081/rest/books/genre")
		.then(response => response.data)
		.then((data) =>
			{
				this.setState({
						genres: [{value:'', display:'Select Genre'}]
								.concat(data.map(genre =>{
									return {value:genre, display:genre}
								}))
						});
			});
	}
	
	submitBook(event)
	{
		alert(this.state.title);
		event.preventDefault();
		
		const book={
			title: this.state.title,
			author: this.state.author,
			coverPhotoURL: this.state.coverPhotoURL,
			isbnNumber: this.state.isbnNumber,
			price: this.state.price,
			language: this.state.language,
			genre: this.state.genre
		};
		
		
		for (var key in book) {
			//  console.log("key " + key + " has value " + book[key]);
			}
		
		axios.post("http://localhost:8081/rest/books",book,
				{
			  headers: {
			    'Content-Type': 'application/json'
			  }
			})
		.then(response =>
				{
					console.log(response);
					if(response.data!=null)
						{
						this.setState({"show":true , "method":"post"});
						setTimeout(()=> this.setState({"show":false}),3000);
						//alert("Saved Successfully");
						}
					else
						{
						this.setState({"show":false});
						}
				});
		
		this.setState(this.intitalState);
	}
	
	//calling arrow function instead  regular function to get hold of this.. or you can also bind this as submit book in constructor
	resetBook=()=>
	{
		this.setState(() => this.intitalState);
	}
	
	bookChange(event) // bookChange=event=>
	{
		this.setState({
			[event.target.name]:event.target.value
		});
	}
	
	bookList=()=>{
		return this.props.history.push("/list");
	};
	
	/*updateBook = event =>{
		
		alert(this.state.title);
		event.preventDefault();
		
		const book={
			id: this.state.id,
			title: this.state.title,
			author: this.state.author,
			coverPhotoURL: this.state.coverPhotoURL,
			isbnNumber: this.state.isbnNumber,
			price: this.state.price,
			language: this.state.language,
		};
		
		
		for (var key in book) {
			 // console.log("key " + key + " has value " + book[key]);
			}
		
		axios.put("http://localhost:8081/rest/books",book,
				{
			  headers: {
			    'Content-Type': 'application/json'
			  }
			})
		.then(response =>
				{
					console.log(response);
					if(response.data!=null)
						{
						this.setState({"show":true , "method":"put"});
						setTimeout(()=> this.setState({"show":false}),3000);
						setTimeout(()=> this.bookList(),3000);
						//alert("Saved Successfully");
						}
					else
						{
						this.setState({"show":false});
						}
				});
		
		this.setState(this.intitalState);
	};*/
	
updateBook = event =>{
		
		alert(this.state.genre);
		event.preventDefault();
		
		const book={
			id: this.state.id,
			title: this.state.title,
			author: this.state.author,
			coverPhotoURL: this.state.coverPhotoURL,
			isbnNumber: this.state.isbnNumber,
			price: this.state.price,
			language: this.state.language,
			genre: this.state.genre
		};
		
		for (var key in book) {
			  console.log("key " + key + " has value " + book[key]);
			}
		
		
		fetch("http://localhost:8081/rest/books",
				{
			method:'PUT',
			body: JSON.stringify(book),
			  headers: {
			    'Content-Type': 'application/json'
			  }
			})
		.then(response => response.json())
		.then(book =>
				{
					console.log(book);
					if(book!=null)
						{
						this.setState({"show":true , "method":"put"});
						setTimeout(()=> this.setState({"show":false}),3000);
						setTimeout(()=> this.bookList(),3000);
						//alert("Saved Successfully");
						}
					else
						{
						this.setState({"show":false});
						}
				});
		
		this.setState(this.intitalState);
	};
	
	render(){
		
		 const {isbnNumber} = this.state;
		
		return (
				
				
				<div>
					<div style={{"display":this.state.show ? "block" : "none"}}> 
						<MyToast children= {{show:this.state.show, message:this.state.method ==="put"? "Book Updated Successfully." :"Book Saved Successfully.", type:"success"}} />
					</div>
					
					<Card className="border border-dark">
					<Card.Header>  <FontAwesomeIcon icon={this.state.id? faEdit:faPlusSquare}/>  
					{this.state.id? "Update Book" :"Add Book"}</Card.Header>
					
					<Form id="bookFormId" onReset={this.resetBook} onSubmit={this.state.id? this.updateBook :this.submitBook}>
					
					<Card.Body>
						<Form.Row>
						  <Form.Group as={Col} controlId="formGridTitle">
						    <Form.Label>Title</Form.Label>
						    <Form.Control required autoComplete="off"
						        type="text" name="title"
						        value={this.state.title || ''}
						    	onChange={this.bookChange}
						    	className={"bg-dark text-white"}
						    	placeholder="Enter Book Title" />
						  </Form.Group>
					   
						  <Form.Group as={Col} controlId="formGridAuthor">
						    <Form.Label>Author</Form.Label>
						    <Form.Control required autoComplete="off"
						    	type="text" name="author"
						    	value={this.state.author}
						    	onChange={this.bookChange}
						    	className={"bg-dark text-white"}
						    	placeholder="Enter Book Author" />
						  </Form.Group>
					    </Form.Row>
					    
					    <Form.Row>
						  <Form.Group as={Col} controlId="formGridIsbnNumber">
						    <Form.Label>ISBN No</Form.Label>
						    <Form.Control required autoComplete="off"
						    	type="text" name="isbnNumber"
						    	value={isbnNumber}
						    	onChange={this.bookChange}
						    	className={"bg-dark text-white"}
						    	placeholder="Enter Book ISBN No" />
						  </Form.Group>
					   
						  <Form.Group as={Col} controlId="formGridPrice">
						    <Form.Label>Price</Form.Label>
						    <Form.Control required autoComplete="off"
						    	type="text" name="price"
						    	value={this.state.price}
						    	onChange={this.bookChange}
						    	className={"bg-dark text-white"}
						    	placeholder="Enter Book Price" />
						  </Form.Group>
					    </Form.Row>
					    
					    <Form.Row>
						  <Form.Group as={Col} controlId="formGridLanguage">
						    <Form.Label>Language</Form.Label>
						    <Form.Control required autoComplete="off"
						    	type="text" name="language"
						    	value={this.state.language}
						    	onChange={this.bookChange}
						    	className={"bg-dark text-white"}
						    	placeholder="Enter Book Language" />
						  </Form.Group>
					   
						  <Form.Group as={Col} controlId="formGridCoverPhotoURL">
						    <Form.Label>Cover Photo URL</Form.Label>
						    <Form.Control required autoComplete="off"
						    	type="text" name="coverPhotoURL"
						    	value={this.state.coverPhotoURL}
						    	onChange={this.bookChange}
						    	className={"bg-dark text-white"}
						    	placeholder="Enter Book Cover Photo URL" />
						  </Form.Group>
					    </Form.Row>
							
					    <Form.Row>
					    <Form.Group as={Col} controlId="formGridGenre">
                        <Form.Label>Genre</Form.Label>
                        <Form.Control required as="select"
                            custom onChange={this.bookChange}
                            name="genre" value={this.state.genre}
                            className={"bg-dark text-white"}>
                            {this.state.genres.map(genre =>
                                <option key={genre.value} value={genre.value}>
                                    {genre.display}
                                </option>
                            )}
                        </Form.Control>
                    </Form.Group>
						</Form.Row>
								
					    
					</Card.Body>
					
					
					<Card.Footer style={{"textAlign":"right"}}>
						<Button size="sm" variant="success" type="submit">
						 <FontAwesomeIcon icon={faSave}/> 
						 {this.state.id? "Update" :"Save"}
						 </Button>
						 {''}
						 <Button size="sm" variant="info" type="reset">
						 <FontAwesomeIcon icon={faUndo}/> Reset
					  </Button>
						 {''}
						 <Button size="sm" variant="info" type="button" onClick={this.bookList.bind()}>
						 <FontAwesomeIcon icon={faList}/> Book List
					  </Button>
						 
					</Card.Footer>
					 
					 </Form>
					 </Card>
					
				</div>
				
				
				
		);
	};
	
}

export default Book;