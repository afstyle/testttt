import axios from 'axios';

function urlSwitch() {
    let protocol = window.location.protocol; //协议
    let host = window.location.host; //主机
    let reg = /^localhost+/;
    if (reg.test(host)) {
        //若本地项目调试使用
        axios.defaults.baseURL = 'http://localhost:9000/';
    } else {
        //动态请求地址             协议               主机
        // axios.defaults.baseURL = protocol + "//" + host  +":9000";
        axios.defaults.baseURL = "http://" + host  +":9000";
    }
}

export default urlSwitch;