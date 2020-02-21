import com.ing.baker.compiler.RecipeCompiler;
import com.ing.baker.il.CompiledRecipe;
import makecoffee.CoffeeMaker;

public class Main {

    public static void main(String[] args) {
        CompiledRecipe recipe = RecipeCompiler.compileRecipe(CoffeeMaker.recipe);
        String visualization = recipe.getRecipeVisualization();
        System.out.print(visualization);
    }
}
