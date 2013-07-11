This is an sbt plugin to allow creation of an sbt project on the fly
desired use 

on the terminal 
>sbt new

and it should create a new project directory structure 

TODO

create plugin archtecture to allow creating of a specific type of project

change key from hello to new 

get rid of annoying println 

write unit tests 

find out how to install as a global plugin

generate basic build.scala template.

allow full text input editiing currently unable to delete on commandline

Install

> git clone https://github.com/adeptues/sbt-plugin.git

then in the sbt plugin directory

>sbt publish-local

after that edit

> .sbt/plugins/plugins.sbt

and add the line

> addSbtPlugin("com.adeptues.org" % "sbt-npr" % "1.0")