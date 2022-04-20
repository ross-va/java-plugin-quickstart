package org.spyne.javapluginquickstart.fooplugin;

import org.spyne.javapluginquickstart.spi.task.Task;

public class TaskImpl implements Task {

  @Override
  public void doTask() {
    System.out.println("I'm a foo dooer!");
  }
}
