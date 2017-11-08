### On Using Ehanced Languages Like Typescript, Pug, and Stylus in .vue files.

_vue-loader_ webpack plugin can automatically use Webpack loaders to process parts of a `.vue` file depending on the `lang` attribute you specify for the file section.

The only requirements are:

1. Install the loaders for the languages you want to use.

        npm install <pug_loader>
        npm install <typescript_loader>
        npm install <stylus_loader>

2. Add the appropriate `lang` attribute to your component sections.

        <template lang="pug">
            // pug code here
        </template>

        <script lang="ts">
            // typescript code here
        </script>

        <style lang="stylus">
            // stylus styles here
        </script>


### Understanding Webpack Output Configurations
```
output: {
    path: path.join(__dirname, '/dist'),
    filename: 'bundle.js',
    publicPath: '/public/'
},
```
Note the following:

1. `output.path` is the _absolute path_ where webpack will write your generated bundles. In our case:

        /absolute/path/to/dist

    * as opposed to _webpack_, _webpack-dev-server_ will not write the generated bundles to this location, instead it __keeps it in memory__ (for optimization reasons). More info on this [S/O answer](https://stackoverflow.com/a/39049619).

2. `output.publicPath` is your server-relative path that maps to your `output.path`. In our case

        localhost:3000/public/ -> /absolute/path/to/dist
            
    So if you have a reference a resource like `<script src="/public/bundle.js">`, the browser will make a request like `GET http://localhost:3000/public/bundle.js`. Then webpack will use the above mapping to fetch the file at `/absolute/path/to/dist/bundle.js`

    > Mapping `output.publicPath` to `output.path`, simply lets you decouple the url of your client-side resources from their actual location on the server.

---
`__dirname` is a global variable in Node.js that returns the absolute path to the currently executing script.

`path.join('a', '/abs/b', 'c')` simply concatenates the supplied paths from left to right using platform-specific separators. For windows, the result will be `a\abs\b\c`, for linux `'a/abs/b/c'`.

`path.resolve('a', '/abs/b', 'c')` will combine the supplied paths from right to left, prepending successively until it encounters an absolute path. So the result in our case will be `'/abs/b/c'`. 

>`path.resolve` always produces an absolute path, it will use your `cwd` as a base to resolve the supplied paths - if none of the supplied paths is an absolute path. For example, assume your cwd (where you ran node from) is `'/users/john/myapp'`, `path.resolve('../assets')` will produce `'/users/john/assets'`

See [this](https://stackoverflow.com/questions/39110801/path-join-vs-path-resolve-with-dirname) and [this](https://stackoverflow.com/a/39836259) S/O answers for more info.