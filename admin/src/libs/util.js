let util = {

};
util.title = function (title) {
    title = title ? title + ' - Admin' : '后台管理系统';
    window.document.title = title;
};

export default util;
