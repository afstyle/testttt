<template>
  <div>
    <Table max-height="500" :columns="columns" :data="tableData"></Table>
  </div>
</template>
<script>
  export default {
      name: 'chapter',
      mounted() {
          let _this = this;
          _this.list();
      },
      data() {
          return{
              columns: [
                  {
                      title: 'ID',
                      key: 'id'
                  },
                  {
                      title: '名称',
                      key: 'name'
                  },
                  {
                      title: '课程ID',
                      key: 'courseId'
                  },
                  {
                      title: '操作',
                      key: ''
                  }
              ],
              tableData: [],
          }
      },
      methods: {
          list() {
            let _this = this;
            _this.$ajax.post('http://127.0.0.1:9000/business/admin/chapter/list', {
                page: 1,
                size: 1
            }).then((response)=>{
                console.log('result = ', response);
                _this.tableData = response.data.list;
            })
          },
      }
  }
</script>