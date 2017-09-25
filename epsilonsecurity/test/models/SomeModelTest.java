package models;

import org.junit.Test;

class SomeModelTest {

    @Test
    public void createAndDeleteObject() {

        new SomeModel(4, 5, "student@sfu.ca").save();

        SomeModel someModel = SomeModel.find.byId(4);

        assert (someModel.id == 4);
    }

}