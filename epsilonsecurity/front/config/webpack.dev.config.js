const path = require('path');

const ENTRY_POINT = '../src/main.js';
const OUTPUT_DIR = '../../public';
const DEV_SERVER_PORT = 3000;

module.exports = {
	entry: path.join(__dirname, ENTRY_POINT),
	output: {
		path: path.join(__dirname, OUTPUT_DIR),
		publicPath: '/',
		filename: 'bundle.js',
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
				options: {}
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
	devServer: {
		port: DEV_SERVER_PORT,
		host: "0.0.0.0",
		disableHostCheck: true, // this is not secure or advised.
	}
};
