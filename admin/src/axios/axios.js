import axios from 'axios';
import utils from '../utils/util';
import urlSwitch from "./switch";
// import Cookies from 'js-cookie';


urlSwitch();
let baseUrl = axios.defaults.baseURL;


const CancelToken = axios.CancelToken;
const source = CancelToken.source();
//设置token
/*function setToken() {
    if (Cookies.get('admin_access_token')) {
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + Cookies.get('admin_access_token');
    }
}*/


//请求验证拦截器
axios.interceptors.request.use(config => {
    // store.commit('UPDATE_LOADING', true); //显示loading,这里没有用vuex可以省去
    //判断请求不是登陆接口的时候,字段是不是过期了,(根据业务逻辑做对应的处理)
    /*if (config.url.indexOf('login') < 0) {
        let lessTime = Number(Cookies.get('expireTime')) - Date.now(); //后台返回的过期时间与现在的进行计算
        if (lessTime <= 0) {
            this.$Message.error({
                content: '登陆过期',
                duration: 5
            });
            source.token('token过期了');
            store.commit('UPDATE_LOADING', false); //隐藏loading
            window.location.href = '/login';
            Cookies.clear();
        }
    }*/
    console.log("请求：", config);
    return config;
});
//返回数据拦截器
axios.interceptors.response.use(res => {
    /*if (res.config.url.indexOf('login') >= 0) {
        const { token, expireTime, code, data } = res.data;
        if (code == '200') {
            //登陆后做出一些本地的处理，按需加载,()
            //...................
            Cookies.set('expireTime', expireTime); //过期时间
            Cookies.set('token', token);
        }
    }
*/
    console.log("返回结果：", res);
    return res;
});

//封装请求方法（put,post,delete）
function formatReq(type, url, data) {
    let _this = this;
    // setToken();
    return new Promise((resolve, reject) => {
        axios({
            method: type,
            url: `${baseUrl}${url}`,
            headers: {
                'content-Type': 'application/json'
            },
            // cancelToken: source.token,
            data: JSON.stringify(data)
        }).then(r => {
            if(r.status === 200){
                //请求成功操作
                resolve(r.data);
            } else {
                //请求失败操作
                resolve(r);
            }

        }).catch(e => {
            _this.$Message.error(e.message);
        });
    });
}
//封装get法
const Http = {
    get: (url, query) => {
        let _this = this;

        url = utils.formatUrl(url, query);
        // setToken();
        return new Promise((resolve, reject) => {
            axios.get(`${baseUrl}${url}`/*, { cancelToken: source.token }*/)
                .then(r => {
                    if (r.status === 200) {
                        resolve(r.data);
                    } else {
                        resolve(r);
                    }
                }).catch(e => {
                    // reject(e.message);
                    _this.$Message.error(e.message);
                })
        })
    },
    post: (url, data) => formatReq('post', url, data),
    put: (url, data) => formatReq('put', url, data),
    patch: (url, data) => formatReq('patch', url, data),
    delete: (url, data) => formatReq('delete', url, data)
};

export default Http;