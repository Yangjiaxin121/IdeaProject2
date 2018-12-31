const webpack              =require('webpack');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
var HtmlWebpackPlugin      = require('html-webpack-plugin');
var path                   = require('path');
//环境变量.dev/online
var WEBPACK_ENV  = process.env.WEBPACK_ENV||'dev_win';
console.log(WEBPACK_ENV);
//获取html-webpack-plugin参数的方法
var getHtmlConfig=function(name,title){
  return{
        filename:'view/'+name+'.html',
        template:'./src/view/'+name+'.html',
        title   : title,
        inject  : true,
        hash    : true,
        chunks  :['common',name]
      };
};
module.exports = {
    entry:{
    'common'                 :['./src/page/common/index.js','webpack-dev-server/client?http://localhost:8080/'],
    'index'                  :['./src/page/index/index.js'],
    'user-login'             :['./src/page/user-login/index.js'],
    'result'                 :['./src/page/result/index.js'],
    'user-register'          :['./src/page/user-register/index.js'],
    'user-pass-reset'        :['./src/page/user-pass-reset/index.js'],
    'user-pass-update'       :['./src/page/user-pass-update/index.js'],
    'shouye'                 :['./src/page/shouye/shouye.js'],
    'user-center'            :['./src/page/user-center/index.js'],
    'user-center-update'     :['./src/page/user-center-update/index.js'],
    'msg'                    :['./src/page/msg/msg.js'],
    'post'                   :['./src/page/post/index.js'],
    // 'spore3'                 :['./src/page/spore3/index.js'],
   }, 
    output:{
        path    : __dirname+'/dist',
        filename: 'js/[name].js'
    },
     externals: {
      'jquery': 'window.jQuery'
    },
      module: {
    rules: [
      { test: /\.css$/, use: [
          MiniCssExtractPlugin.loader,
          'css-loader'
        ]},
       {test  : /\.(gif|jpg|png|woff|svg|eot|ttf|mp3|mp4)\??.*$/, 
        loader: 'url-loader?limit=40000000&name=resource/[name].[ext]'},
        {test : /\.(string)$/, loader: 'html-loader' },
    //     { test: /\.(png|jpg|gif)$/, 
    //       use: [{ loader: 'file-loader', 
    //     options: { name: '[name].[ext]', publicPath: "/image", outputPath: "images/" } 
    //   } 
    //   ] 
    // },
         // {
         //      test: /\.(html)$/,
         //      use: {
         //           loader: 'html-loader',
         //           options: {
         //                 attrs: ['img:src', 'img:data-src', 'audio:src'],
         //                 minimize:true
         //           }
         //     }
         //  }
    ]
  },
       optimization: {
    splitChunks: {
      cacheGroups: {
        common: {
          name     : "common",
          chunks   : "all",
          minSize  : 1,
          priority : 0
        },
      }
    }
  },
  resolve:{
    alias:{
      util    : __dirname + 'src/util',
      page    : __dirname + 'src/page',
      service : __dirname + 'src/service',
      imagem     : __dirname + 'src/image',
    }
  },
  plugins:[
  new webpack.HotModuleReplacementPlugin(),
   new MiniCssExtractPlugin({
      filename: 'css/[name].css',
    }),
   new HtmlWebpackPlugin(getHtmlConfig('index','首页')),
   // new HtmlWebpackPlugin(getHtmlConfig('spore-post1','')),
   new HtmlWebpackPlugin(getHtmlConfig('spore-post2','抑郁的本质')),
   new HtmlWebpackPlugin(getHtmlConfig('spore-post3','')),
   new HtmlWebpackPlugin(getHtmlConfig('spore-post4','')),
   new HtmlWebpackPlugin(getHtmlConfig('spore-post5','')),
   new HtmlWebpackPlugin(getHtmlConfig('post11','上传')),
   new HtmlWebpackPlugin(getHtmlConfig('page001','')),
   new HtmlWebpackPlugin(getHtmlConfig('spore-post8','')),
   new HtmlWebpackPlugin(getHtmlConfig('spore-post9','')),
   new HtmlWebpackPlugin(getHtmlConfig('user-login','用户登陆')),
   new HtmlWebpackPlugin(getHtmlConfig('user-register','用户注册')),
   new HtmlWebpackPlugin(getHtmlConfig('user-pass-reset','找回密码')),
   new HtmlWebpackPlugin(getHtmlConfig('user-pass-update','修改密码')),
   new HtmlWebpackPlugin(getHtmlConfig('result','操作结果')),
   new HtmlWebpackPlugin(getHtmlConfig('user-center','用户中心')),
   new HtmlWebpackPlugin(getHtmlConfig('user-center-update','修改个人信息')),
   new HtmlWebpackPlugin(getHtmlConfig('page','文章展示')),
   ]
 };
if(WEBPACK_ENV === 'dev'){
    (module.exports).app.push('webpack-dev-server/client?http://localhost:8080');
}