package dk.getonboard.android.bakingtime.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dk.getonboard.android.bakingtime.model.Ingredient;
import dk.getonboard.android.bakingtime.model.Recipe;
import dk.getonboard.android.bakingtime.model.Step;

public class JsonParser {

    private static List<Step> parseSteps(JSONArray stepsJsonArray) {
        List<Step> stepList = new ArrayList<>();
        try {
            for(int i = 0; i < stepsJsonArray.length(); i++) {
                JSONObject stepJsonObject = stepsJsonArray.getJSONObject(i);
                Step step = new Step();
                step.setId(stepJsonObject.getInt("id"));
                step.setShortDescription(stepJsonObject.getString("shortDescription"));
                step.setDescription(stepJsonObject.getString("description"));
                step.setVideoURL(stepJsonObject.getString("videoURL"));
                step.setThumbnailUrl(stepJsonObject.getString("thumbnailURL"));
                stepList.add(step);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return stepList;
    }

    private static List<Ingredient> parseIngredients(JSONArray ingredientsJsonArray) {
        List<Ingredient> ingredientList = new ArrayList<>();
        try {
            for(int i = 0; i < ingredientsJsonArray.length(); i++) {
                JSONObject ingredientJsonObject = ingredientsJsonArray.getJSONObject(i);
                Ingredient ingredient = new Ingredient();
                ingredient.setQuantity(ingredientJsonObject.getInt("quantity"));
                ingredient.setMeasure(ingredientJsonObject.getString("measure"));
                ingredient.setIngredient(ingredientJsonObject.getString("ingredient"));
                ingredientList.add(ingredient);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ingredientList;
    }

    private static Recipe parseRecipe(JSONObject recipeJsonObject) {
        Recipe recipe = new Recipe();
        try {
            recipe.setId(recipeJsonObject.getInt("id"));
            recipe.setName(recipeJsonObject.getString("name"));
            recipe.setIngredients(parseIngredients(recipeJsonObject.getJSONArray("ingredients")));
            recipe.setSteps(parseSteps(recipeJsonObject.getJSONArray("steps")));
            recipe.setServings(recipeJsonObject.getInt("servings"));
            recipe.setImage(recipeJsonObject.getString("image"));
            recipe.setTimeInMinutes(recipeJsonObject.getInt("time"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public static List<Recipe> parseRecipes(String json) {
        List<Recipe> recipeList = new ArrayList<>();
        try {
            JSONArray recipesJsonArray = new JSONArray(json);
            for(int i = 0; i < recipesJsonArray.length(); i++) {
                recipeList.add(parseRecipe(recipesJsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return recipeList;
    }
}
