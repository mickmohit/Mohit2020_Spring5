import React from 'react';
import axios from 'axios';

import {Card,Table,Image,InputGroup,FormControl,Button,Alert} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faUsers,faStepBackward,faFastBackward,faStepForward,faFastForward} from '@fortawesome/free-solid-svg-icons';
import './Style.css';

import {connect} from 'react-redux';
import {fetchUsers} from '../services/user/userActions';


class UserList extends React.Component
{
	
	constructor(props){
		super(props);
		this.state={
		users: [],
		currentPage: 1,
		userPerPage: 5
		};
	}
	
	componentDidMount()
	{
		this.findAllRandomUsers(); // for making axios call to external URL
	//	this.props.fetchUsers();   // for making redux call to external URL
	}
	
	findAllRandomUsers()
	{
		axios.get("https://randomapi.com/api/6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole")
		 .then(response => response.data)
		 .then((data) => {
					this.setState({users: data}); 
				 }); 
	}
	
	changePange = event =>
	{
		this.setState({
			[event.target.name]: parseInt(event.target.value)
		});
	};
	
	firstPage = () =>
	{
		if(this.state.currentPage>1){
			this.setState({
				currentPage:1
			});
		}
	};
	
	prevPage = () =>
	{

		if(this.state.currentPage>1){
			this.setState({
				currentPage:this.state.currentPage-1
			});
		}
	};
	
	lastPage = () =>
	{
		if(this.state.currentPage<Math.ceil(this.state.users.length / this.state.userPerPage)){
			this.setState({
				currentPage:Math.ceil(this.state.users.length / this.state.userPerPage)
			});
		}
	};
	
	nextPage = () =>
	{
		if(this.state.currentPage<Math.ceil(this.state.users.length / this.state.userPerPage)){
			this.setState({
				currentPage:this.state.currentPage+1
			});
		}
	};
	
 render()
 	{
	 
	 const {users,currentPage,userPerPage} = this.state;
	 
	/* const userData = this.props.userData;
     const users = userData.users;*/
	 
	 const lastIndex=currentPage*userPerPage;
	 const firstIndex=lastIndex-userPerPage;
	 const currentUsers = users.slice(firstIndex,lastIndex);
	 const totalPage=users.length/userPerPage;
	 
	
	 
	/* const pageNumCss = {
		width:"45px",
		border:"1px solid #17A288",
		color:"17A288",
		textAlign:"center",
		fontWeight: "bold",
	 };*/
	 
	 	return(
	 	<div>
	 	
	 	
	 	
	 	<Card className="border border-dark">
		<Card.Header> <FontAwesomeIcon icon={faUsers}/> User List</Card.Header>
		<Card.Body>
			<Table bordered hover striped variant="dark">
			
			<thead>
		    <tr>
		    	<td>Name</td>
		    	<td>Email</td>
		    	<td>Address</td>
		    	<td>Created</td>
		    	<td>Balance</td>
		    </tr>
		    </thead>
		    <tbody>
		    {users.length === 0 ?
		    <tr align="center">
		    	<td colSpan="6">No Users Available
		    </td></tr>
		    	:
		    	
		    		currentUsers.map((user,index) =>(
		    			<tr key={index}>
			    		<td>{user.first}</td>
			    		<td>{user.email}</td>
			    		<td>{user.address}</td>
			    		<td>{user.created}</td>
			    		<td>{user.balance}</td>
			    		</tr>
				    ))   
		    }
		    </tbody>
		    </Table>
		</Card.Body>
		
		<Card.Footer>
			<div style={{"float":"left"}}>
			Showing Page {currentPage} of {totalPage}
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
					
					<FormControl className={"page-num bg-dark"} name="currentPage" value={currentPage} onChange={this.changePange}/>
					
					<InputGroup.Prepend>
							
						<Button type="button" variant="outline-info" disabled={currentPage ===totalPage ? true:false} onClick={this.nextPage}>
						<FontAwesomeIcon icon={faStepForward}/> Next
						</Button>
						
						<Button type="button" variant="outline-info" disabled={currentPage ===totalPage ? true:false} onClick={this.lastPage}>
						<FontAwesomeIcon icon={faFastForward}/>	Last
						</Button>
					
					</InputGroup.Prepend>
					
				</InputGroup>
			</div>
		</Card.Footer>
		</Card> 
	 	
	 	
	 	</div>
	 	);
 	}
}

/* for redux const mapStatetoProps = state =>
{
	return {
		userData: state.user
	}
};

const mapDispatchtoProps = dispatch =>
{
	return {
		fetchUsers: () => dispatch(fetchUsers())
		}
};
export default connect(mapStatetoProps,mapDispatchtoProps) (UserList);*/

export default UserList;
