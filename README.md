# lein-gwt

A Leiningen plugin for running the Google Web Toolkit compiler.

## Usage

Add a list of the GWT modules you want to compile to your project.clj:

    (defproject myproject "1.0-SNAPSHOT"
      :gwt-modules ["mypackage.MyGWTModule"])

Now you can run the GWT compiler by invoking `lein gwt`.

### Passing arguments

To customize the compiler invocation, add a map `:gwt-options` to project.clj. See the GWT documentation for the options you can pass, e.g.:
 
   (defproject myproject "1.0-SNAPSHOT"
      :gwt-modules ["mypackage.MyGWTModule"]
      :gwt-options {:localWorkers 2, :war "my/output/dir"})

## Installation

FIXME: write

## License

Copyright (C) 2010 Tero Parviainen

Distributed under the MIT license (see [LICENSE]).
