const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const HtmlWebpackHarddiskPlugin = require('html-webpack-harddisk-plugin');

const ENTRY_POINT = '../src/main.js';
const OUTPUT_DIR = '../../public';
const TEMPLATE_SRC = '../src/index.html';
const DEV_SERVER_PORT = 3000;

module.exports = {
	entry: path.join(__dirname, ENTRY_POINT),
	output: {
		path: path.join(__dirname, OUTPUT_DIR),
		publicPath: '/',
		filename: 'bundle.[hash].js',
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
	plugins: [
		new HtmlWebpackPlugin({
			template: path.join(__dirname, TEMPLATE_SRC),
			alwaysWriteToDisk: true
		}),
		new HtmlWebpackHarddiskPlugin({
			outputPath: path.resolve(__dirname, OUTPUT_DIR)
		})
	],
	devServer: {
		port: DEV_SERVER_PORT,

		// by default, dev-server only listens for requests from ip=127.0.0.1 aka 'localhost' (which is vagrant guest)
		// so if we must access dev-server from another machine (like the host running vagrant), we must configure
		// dev-server to listen for requests from all IPs (0.0.0.0 is the 'all' ip)
		// host: "0.0.0.0",

		// some filesystems might not broadcast notifications
		// when files change, so make dev-server poll for changes.
		// (necessary for vagrant vm - https://webpack.js.org/configuration/dev-server/#devserver-watchoptions-)
		// watchOptions: {
		// 	poll: 1000 // msec
		// },
		// when dev-server embedds utility script in bundle for HMR,
		// the utility uses the url from browser address bar to query dev-server.
		// in the guest vm, it might be localhost:3000, but since the utility
		// executes on the host, GET localhost:3000 might fail because the host
		// there is no dev-server running on the host at 3000, what we need is to
		// inform the utility script to use the forwarding port of the host machine.
		public: "localhost:3000" // comment-out if not using Vagrant
	}
};