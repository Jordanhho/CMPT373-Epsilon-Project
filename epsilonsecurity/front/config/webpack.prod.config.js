const path = require('path');
const webpack = require('webpack');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const CleanWebpackPlugin = require('clean-webpack-plugin');

const ENTRY_POINT = '../src/main.js';
const OUTPUT_DIR = '../../public/bundle';

module.exports = {
		entry: path.join(__dirname, ENTRY_POINT),
    output: {
				path: path.join(__dirname, OUTPUT_DIR),
        filename: 'bundle.js'
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
						extractCSS: true
					}
				},
				{
					test: /\.(png|jpg|gif|svg)$/,
					loader: 'file-loader',
					options: {
						name: '[name].[ext]?[hash]'
					}
				}
			]
    },
    plugins:[
			new webpack.DefinePlugin({
				'process.env.NODE_ENV': JSON.stringify('production')
			}),
			new CleanWebpackPlugin([OUTPUT_DIR], { allowExternal: true }),
			new ExtractTextPlugin("bundle.css")
    ]
};
