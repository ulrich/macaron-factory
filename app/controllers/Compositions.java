package controllers;

import java.util.List;

import models.Composition;

public class Compositions extends Application {

   public static void index() {
      List<Composition> compositions = Composition.all().fetch();
      render(compositions);
   }

   public static void show(Long id) {
      Composition composition = Composition.findById(id);
      render(composition);
   }
}
