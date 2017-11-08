'use strict';
const path = require('path');
const webpack = require('webpack');
const ExtractTextPlugin = require("extract-text-webpack-plugin");
const OptimizeCssAssetsPlugin = require('optimize-css-assets-webpack-plugin');
const CompressionPlugin = require('compression-webpack-plugin');

module.exports = {
    entry: path.resolve('./src/main.js'),
    output: {
        path: path.resolve('../public/bundle'),
//        filename: 'js.bundle.[hash].js'
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
        new webpack.optimize.UglifyJsPlugin({
            sourceMap: true
        }),
//        new ExtractTextPlugin("style.bundle.[contentHash].css"),
        new ExtractTextPlugin("bundle.css"),
        new OptimizeCssAssetsPlugin({
            cssProcessor: require('cssnano'),
            cssProcessorOptions: { discardComments: {removeAll: true } }
        }),
//        new CompressionPlugin({
//            asset: "[path].gz[query]",
//            test: /\.(js|css)$/,
//            minRatio: 0.8,
//            deleteOriginalAssets: false
//        })
    ]
};
