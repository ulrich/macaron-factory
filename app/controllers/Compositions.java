package controllers;

import java.util.List;

import models.Composition;

public class Compositions extends Application {

   public static void index() {
      List<Long> compositionIds = Composition.find("select id from Composition").fetch(1, 1);
      if ((compositionIds != null) && !(compositionIds.isEmpty())) {
         show(compositionIds.get(0));
      }
      render();
   }

   public static void show(Long id) {
      Composition composition = Composition.findById(id);
      List<Composition> compositions = Composition.all().fetch();
      render(composition, compositions);
   }
}
