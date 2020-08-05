import React from 'react';
import Home from './components/Home';
import Category from './components/Category';
import Expenses from './components/Expenses';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.css';


function App() {
  return (
		  <Router>
		  <Switch>
		  <Route path='/' exact={true} component={Home}/>
		  <Route path='/categories' exact={true} component={Category}/>
		  <Route path='/expenses' exact={true} component={Expenses}/>
		  </Switch>
		  </Router>	  	   
  );
}

export default App;
