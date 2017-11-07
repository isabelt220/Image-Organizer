package AppComponents;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TagTest {

    @Test
    void testTagAddImage() {
        Tag ham = new Tag("ham");
        ImageInfo hamImage = new ImageInfo("@ham", "C://Photo");
        ham.addImage(hamImage);
        assertTrue(ham.getAssociatedImages().size() == 1);
        assertTrue(ham.getAssociatedImages().contains(hamImage));
    }

    @Test
    void testTagRemoveImage() {
        Tag breakfast = new Tag("breakfast");
        ImageInfo hamImage = new ImageInfo("@ham @breakfast", "C://Photo");
        ImageInfo baconImage = new ImageInfo("@bacon @breakfast", "C://Photo");
        ImageInfo cheeseImage = new ImageInfo("@cheese @breakfast", "C://Photo");
        breakfast.addImage(hamImage);
        breakfast.addImage(baconImage);
        breakfast.addImage(cheeseImage);

        assertTrue(breakfast.getAssociatedImages().size() == 3);

        ArrayList<ImageInfo> foodList = new ArrayList<>();
        foodList.add(hamImage);
        foodList.add(baconImage);
        foodList.add(cheeseImage);

        assertEquals(foodList, breakfast.getAssociatedImages());
    }

    @Test
    void testTagEquals() {
        Tag ham = new Tag("ham");
        Tag ham2 = new Tag("Ham");
        Tag cheese = new Tag("cheese");
        assertEquals(ham, ham2);
        assertFalse(ham.equals(cheese));
    }

}
