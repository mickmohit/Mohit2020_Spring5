import React, {Component} from 'react';
import Navigation from './Navigation';

class Home extends Component
{
	state ={}
		render(){
			return(
			<div>
			<Navigation />
			<h2 style={{display: 'flex',  justifyContent:'center', alignItems:'center', height: '100vh'}}>
			Welcome to Expense Home </h2>
			</div>
			);
		}
}

export default Home;