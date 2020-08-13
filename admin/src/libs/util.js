let util = {

};
util.title = function (title) {
    title = title ? title + ' - Home' : '后台管理';
    window.document.title = title;
};

export default util;
