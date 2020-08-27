const utils = {}

utils.formatUrl = function (url, data) {
    let ret = '?';
    if (data){
        for (let key in data) {
            ret += encodeURIComponent(key) + '=' + encodeURIComponent(data[key]) + '&';
        }
        ret = ret.substr(0,ret.length - 1);
    }

    return url + ret;
};

export default utils;