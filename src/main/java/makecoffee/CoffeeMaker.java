package makecoffee;

import com.ing.baker.recipe.annotations.FiresEvent;
import com.ing.baker.recipe.annotations.RequiresIngredient;
import com.ing.baker.recipe.javadsl.Interaction;
import com.ing.baker.recipe.javadsl.Recipe;

import static com.ing.baker.recipe.javadsl.InteractionDescriptor.of;

public class CoffeeMaker {

    public static class CoffeeIngredients {

        public final String water;
        public final String beans;

        public CoffeeIngredients(String water, String beans){
            this.water = water;
            this.beans = beans;
        }
    }

    public interface CoffeeMachine extends Interaction {

        interface CoffeeMachineOutcome{
        }

        class NotEnoughBeans implements CoffeeMachineOutcome {
            public String notEnoughBeans;
            public NotEnoughBeans(String notEnoughBeans){
                this.notEnoughBeans = notEnoughBeans;
            }
        }

        class NotEnoughWater implements CoffeeMachineOutcome {
            public String notEnoughWater;
            public NotEnoughWater(String notEnoughWater){
                this.notEnoughWater = notEnoughWater;
            }
        }

        class CoffeeMachineSuccessful implements CoffeeMachineOutcome {
            public String coffee;
            public CoffeeMachineSuccessful(String coffee){
                this.coffee = coffee;
            }
        }

        @FiresEvent(oneOf = {CoffeeMachineSuccessful.class, NotEnoughWater.class, NotEnoughBeans.class})
        CoffeeMachineOutcome apply(
                @RequiresIngredient("beans") String beans,
                @RequiresIngredient("water") String water
        );
    }

    public final static Recipe recipe = new Recipe("MakeCoffee")
            .withSensoryEvents(CoffeeIngredients.class)
            .withInteractions(of(CoffeeMachine.class));

}
