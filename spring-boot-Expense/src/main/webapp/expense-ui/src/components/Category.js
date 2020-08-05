import React, {Component} from 'react';
import Navigation from './Navigation';


class Category extends Component
{
	state={
		isLaoding: true,
		Categories: []
	}
	
	async componentDidMount(){
		const response = await fetch('/api/categories')
		const body = await response.json();
		this.setState({Categories :body , isLaoding :false});
	}
	
	render(){
		
		const {Categories, isLaoding} = this.state;
		if(isLaoding)
			return(<div>Loading...</div>);
		
		return(
		<div>
		<Navigation />
			<h2>Categories</h2>
			{
				Categories.map( category =>
					<div id={category.id}>
					  {category.name}
					</div>
				)
			}
		</div>
		);
	}
}

export default Category;