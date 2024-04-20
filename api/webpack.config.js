var path = require('path');

module.exports = {
    entry: './src/main/js/index.js',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    mode: 'development',

    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader",
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader'],
            },
            {
                test: /\.(woff|woff2)$/,
                type: 'asset/resource',
                generator: {
                    filename: 'src/main/resources/static/fonts/[name][ext]', // Specifies the output directory for font files
                },
            },
        ],
    },
};