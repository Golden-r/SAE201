#!/bin/bash

if (! [ -d "class" ]); then
  mkdir class
fi

javac -d  class @compile.list
java  -cp class controleur.Controleur "$1"