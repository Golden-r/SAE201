#!/bin/bash

javac -d  class @compile.list
java  -cp class controleur.Controleur "$1"