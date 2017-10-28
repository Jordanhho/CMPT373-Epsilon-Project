'use strict';
let path = require('path');

module.exports = {
	entry: path.resolve('./src/main.js'),
	output: {
			path: path.resolve('../public/js'),
			filename: 'build.js',
			// publicPath: '/'
	},
	resolve: {
		extensions: ['.js', '.vue', '.json'],
		alias: {
			'vue$': 'vue/dist/vue.esm.js',
		}
	},
	module: {
		rules: [
			{
				test: /\.js$/,
				loader: 'babel-loader',
				exclude: /node_modules/
			},
			{
				test: /\.vue$/,
				loader: 'vue-loader',
				options: {
          loaders: {
            // Since sass-loader (weirdly) has SCSS as its default parse mode, we map
            // the "scss" and "sass" values for the lang attribute to the right configs here.
            // other preprocessors should work out of the box, no loader config like this necessary.
            'scss': 'vue-style-loader!css-loader!sass-loader',
            'sass': 'vue-style-loader!css-loader!sass-loader?indentedSyntax'
					}
					// other options here.
				}
			},
			// transform our own css files
			{
				test:  /\.s[a|c]ss$/,
				// exclude: /node_modules/,
				loader: 'style-loader!css-loader!sass-loader'
			},
			// // do not transform vendor css files
			// {
			// 	test: /\.css$/,
			// 	include: /node_modules/,
			// 	loaders: ['style-loader','css-loader'],
			// },
			{
				test: /\.(png|jpg|gif|svg)$/,
				loader: 'file-loader',
				options: {
					name: '[name].[ext]?[hash]'
				}
			},
			{
        test: /\.styl$/,
        loader: ['style-loader', 'css-loader', 'stylus-loader']
			}
		]
	}
};