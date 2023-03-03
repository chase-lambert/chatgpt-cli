## Small little cli app using the new OpenAI chatGPT API.

To run use 'clojure -M -m chatgpt-cli.core' or build an 
uberjar with `clj -T:build uber` and run with 
`java -jar target/chatgpt-cli-standalone.jar`

I would just use bababska though with this repo's corresponding script:
`https://gist.github.com/chase-lambert/c5533d8e8fbb71268a25e83ecf8e3cc6`
You can find bababska here if you haven't tried it out yet:
`https://github.com/babashka/babashka`

Make sure you have `OPEN_API_KEY` set on your system.
Put something like `export OPEN_API_KEY=<your key>` in 
your .bashrc

I'm using bash in debian so no clue if it works on Mac or Windows.
