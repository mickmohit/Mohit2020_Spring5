import React from 'react';
import {Jumbotron} from 'react-bootstrap';


class Welcome extends React.Component
{
render()
	{
	return(
			 <Jumbotron className="bg-dark text-white">
		     <h1>Welcome to Book Shop</h1>
		     <p>
		       Oh Boy!
		     </p>
		    
		   </Jumbotron>
		  );
	}
}

export default Welcome;