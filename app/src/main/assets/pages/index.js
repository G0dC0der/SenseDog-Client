load('./libraries/system.src.js',
load('./libraries/config/app.config.json',
load('./libraries/config/dev.config.json',
load('./libraries/config/package.config.json', initialize))), true);

var hmr;

function initialize(){
    System.import('core-js')
          .then(() => {System.import('zone.js')})
          .then(() => {System.import('reflect-metadata')})
          .then(() => {System.import('systemjs-hmr')})
          .then(() => {System.import('main')})
          .catch(console.error.bind(console));
    document.getElementById("reload")
            .addEventListener("click", event => location.replace('pages/index.html'));
    watch();
}
function load(src,callback,loadNow){
    if (loadNow) {
        let type='text/javascript',
            script=document.createElement('script');
            script.onload=callback;
            script.type=type;
            script.src=src;
        document.head.appendChild(script);
    } else { return () => load(src, callback, true); }
}
function watch(){
    if (window.ng) {
        load('./libraries/custom/hot-module-reloader.js', 
        ()=>{hmr = new HotModuleReloader()}, true)
    } else { setTimeout(watch, 5000) }
}