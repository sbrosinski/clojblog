# Clojblog

WORK IN PROGRESS!

A sample project I used to learn Clojure.
It's a blog web app which reads markdown formatted blog posts from the file system and
renders them as a list of posts with links to individual posts.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server

## Docker Container

docker build -t clojblog .

## License

Copyright © 2016 Stephan Brosinski
