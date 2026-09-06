import React from 'react';

function RecipeGenerator() {

  const [ingredients , setIngredients] = React.useState('');
  const [cuisine , setCuisine] = React.useState('');
  const [dietaryRestrictions , setDietaryRestrictions] = React.useState('');
  const [recipe , setRecipe] = React.useState('');

  const createRecipe = async() => {
		 try{
			//const response = await fetch('http://localhost:8080/recipe-creator?ingredients=${ingredients}&cuisine=${cuisine}&dietaryRestrictions=${dietaryRestrictions}');  //make API call to backened server using fetch
            const response = await fetch(`/api/recipe-creator?ingredients=${encodeURIComponent(ingredients)}&cuisine=${encodeURIComponent(cuisine)}&dietaryRestrictions=${encodeURIComponent(dietaryRestrictions)}`);
            const data = await response.text();
			console.log(data);
			setRecipe(data);  //update recipe to state
		 }
		 catch(error)
		 {
			console.error('Error generating recipe:', error);
		 }
  }; 
	return (
		<div>
		<h2>Create Recipe</h2>
		
        <input type="text" value={ingredients} onChange={(e) => setIngredients(e.target.value)} 
		placeholder="Enter ingredients(comma separated)" />

		<input type="text" value={cuisine} onChange={(e) => setCuisine(e.target.value)} 
		placeholder="Enter cuisine type" />

		<input type="text" value={dietaryRestrictions} onChange={(e) => setDietaryRestrictions(e.target.value)} 
		placeholder="Enter dietary restrictions" />

       		<button onClick={createRecipe}>Create Recipe</button>
			<div className="output">
				<pre className="recipe-text">{recipe}</pre>
			</div>
		</div>
	);
}

export default RecipeGenerator;
