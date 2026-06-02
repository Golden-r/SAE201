#!/bin/bash

javac -d  class @compile.list
java  -cp class Controleur "$1"