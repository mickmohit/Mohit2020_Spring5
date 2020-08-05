import React from 'react';
import {Card,Table,Image, Button, ButtonGroup,InputGroup,FormControl} from 'react-bootstrap';

import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList,faEdit,faTrash,faStepBackward,faFastBackward,faStepForward,faFastForward,faSearch,faTimes} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import {MyToast} from './MyToast';
import {Link} from 'react-router-dom';

class Booklist extends React.Component
{
	
	constructor(props){
		super(props);
	
		this.state={
		books: [],
		currentPage: 1,
		bookPerPage: 5,
		sortToggle: true,
		search: ''
		};
		this.state.show=false;
	}
	
	componentDidMount()
	{
		this.findAllBooks(this.state.currentPage);
	}
	
	sortData = () =>
	{
		this.setState(state => ({
			sortToggle: !state.sortToggle
		}) );
		this.findAllBooks(this.state.currentPage);
	}
	
	findAllBooks(currentPage)
	{
		currentPage-=1;
		let sortDir = this.state.sortToggle ? "asc" : "desc";
		
		//axios.get("http://localhost:8081/rest/books") for normal requests
		//axios.get("http://localhost:8081/rest/books?page="+currentPage+"&size="+this.state.bookPerPage)  // for pagination requests not sorting
		axios.get("http://localhost:8081/rest/books?pageNumber="+currentPage+"&pageSize="+this.state.bookPerPage+"&sortBy=title&sortDir="+sortDir) // for pagination & sorting requests both at once
		 .then(response => response.data)
		 .then((data) => {
					this.setState({
						books: data.content,
						totalPages: data.totalPages,
						totalElements: data.totalElements,
						currentPage: data.number+1
					});  //change data to data.content for pagination request
				 }); 
	
	}
	
	deleteBook(bookId)
	{
		alert(bookId);
		axios.delete("http://localhost:8081/rest/books/"+bookId)
		.then(response => {
			if(response.data!=null)
			{
			//alert("deleting");
			this.setState({"show":true});
			setTimeout(()=> this.setState({"show":false}),3000);
			//below code is to remove bookid from book array
			this.setState(
					{
				books: this.state.books.filter(book =>book.id !==bookId)
				});
			} 
				else
				{
				this.setState({"show":false});
			 	}
		});
	}
	
	searchChange = event => {
        this.setState({
            [event.target.name] : event.target.value
        });
    };
    
    cancelSearch = () => {
        this.setState({"search" : ''});
        this.findAllBooks(this.state.currentPage);
    };
    
    searchData = (currentPage) => {
        currentPage -= 1;
        axios.get("http://localhost:8081/rest/books/search/"+this.state.search+"?page="+currentPage+"&size="+this.state.bookPerPage)
            .then(response => response.data)
            .then((data) => {
                this.setState({
                    books: data.content,
                    totalPages: data.totalPages,
                    totalElements: data.totalElements,
                    currentPage: data.number + 1
                });
            });
    };
	
	changePage = event =>
	{
		if(this.state.search)
			{
			this.searchData(parseInt(event.target.value));
			}
		else
			{
			this.findAllBooks(parseInt(event.target.value));
			}
		
		this.setState({
			[event.target.name]: parseInt(event.target.value)
		});
	};
	
	firstPage = () =>
	{
		if(this.state.currentPage>1){
			if(this.state.search)
			{
			this.searchData(1);
			}
		else
			{
			this.findAllBooks(1);
			}
		}
	};
	
	prevPage = () =>
	{

		if(this.state.currentPage>1){
			if(this.state.search)
			{
			this.searchData(this.state.currentPage - 1);
			}
		else
			{
			this.findAllBooks(this.state.currentPage - 1);
			}
		}
	};
	
	lastPage = () =>
	{
		let condition = Math.ceil(this.state.totalElements / this.state.bookPerPage);
		if(this.state.currentPage < condition){
			if(this.state.search)
			{
			this.searchData(condition);
			}
		else
			{
			this.findAllBooks(condition);
			}
		}
	};
	
	nextPage = () =>
	{
		if(this.state.currentPage<Math.ceil(this.state.totalElements / this.state.bookPerPage)){
			if(this.state.search)
			{
			this.searchData(this.state.currentPage+1);
			}
		else
			{
			this.findAllBooks(this.state.currentPage+1);
			}
		}
	};
	
	render(){
		
		 const {books,currentPage, totalPages} = this.state;
		 const pageNumCss = {
					width:"45px",
					border:"1px solid #17A288",
					color:"17A288",
					textAlign:"center",
					fontWeight: "bold",
				 };
		
		return (
				
			<div>
				<div style={{"display":this.state.show ? "block" : "none"}}> 
					<MyToast children= {{show:this.state.show, message:"Book Deleted Successfully.", type:"danger"}} />
				</div>
					<Card className="border border-dark bg-dark text-white">
					<Card.Header> 
					<div style={{"float":"left"}}>
                    <FontAwesomeIcon icon={faList} /> Book List
                </div>
                <div style={{"float":"right"}}>
                     <InputGroup size="sm">
                        <FormControl placeholder="Search" name="search" value={this.state.search}
                            className={"info-border bg-dark text-white"}
                            onChange={this.searchChange}/>
                        <InputGroup.Append>
                            <Button size="sm" variant="outline-info" type="button" onClick={this.searchData}>
                                <FontAwesomeIcon icon={faSearch}/>
                            </Button>
                            <Button size="sm" variant="outline-danger" type="button" onClick={this.cancelSearch}>
                                <FontAwesomeIcon icon={faTimes} />
                            </Button>
                        </InputGroup.Append>
                     </InputGroup>
                </div>
					
					</Card.Header>
					<Card.Body>
						<Table bordered hover striped variant="dark">
						
						<thead>
					    <tr>
					    
					      <th>Title</th>
					      <th>Author</th>
					      <th>ISBN No</th>
					      <th onClick={this.sortData}>Price<div className={this.state.sortDir === "asc" ? "arrow-up" : "arrow-down"}></div></th>
                          <th>Language</th>
                          <th>Genre</th>
					      <th>Actions</th>
					    </tr>
					  </thead>
					  <tbody>
					  
					  {
						this.state.books.length === 0? 
					    <tr align="center">
					     <td colSpan={6}> Books Not Available</td>
					    </tr> :
					    	this.state.books.map((book) =>(
					    <tr key={book.id}>
					    <td>
					    	<Image src={book.coverPhotoURL} roundedCircle width="25" height="25"/>
					        {book.title}
					    </td>
					    <td>{book.author}</td>
					    <td>{book.isbnNumber}</td>
					    <td>{book.price}</td>
					    <td>{book.language}</td>
					    <td>{book.genre}</td>
					    <td>
					    <ButtonGroup>
					    	<Link to={"edit/"+book.id} className="btn btn-sm btn-outline-primary"><FontAwesomeIcon icon={faEdit}/></Link>
					    	<Button size="sm"  variant="outline-danger" onClick={this.deleteBook.bind(this, book.id)}><FontAwesomeIcon icon={faTrash}/></Button>
					    </ButtonGroup>
					    </td>
					    </tr>
					    ))
						}
					  </tbody>
						
						</Table>
					</Card.Body>
					
					{books.length > 0 ?
					<Card.Footer>
					<div style={{"float":"left"}}>
					Showing Page {currentPage} of {totalPages}
					</div>
					<div style={{"float":"right"}}>
						<InputGroup>
							<InputGroup.Prepend>
							
								<Button type="button" variant="outline-info" disabled={currentPage ===1 ? true:false} onClick={this.firstPage}>
								<FontAwesomeIcon icon={faFastBackward}/>	First
								</Button>
								
								<Button type="button" variant="outline-info" disabled={currentPage ===1 ? true:false} onClick={this.prevPage}>
								<FontAwesomeIcon icon={faStepBackward}/>	Prev
								</Button>
								
							</InputGroup.Prepend>
							
							<FormControl style={pageNumCss} className={"bg-dark"} name="currentPage" value={currentPage} onChange={this.changePage}/>
							
							<InputGroup.Prepend>
									
								<Button type="button" variant="outline-info" disabled={currentPage ===totalPages ? true:false} onClick={this.nextPage}>
								<FontAwesomeIcon icon={faStepForward}/> Next
								</Button>
								
								<Button type="button" variant="outline-info" disabled={currentPage ===totalPages ? true:false} onClick={this.lastPage}>
								<FontAwesomeIcon icon={faFastForward}/>	Last
								</Button>
							
							</InputGroup.Prepend>
							
						</InputGroup>
					</div>
				</Card.Footer> : null
				}
				</Card>
					
			</div>
			
				
		);
	};
	
	
}

export default Booklist;