<template>
  <div>
    <Row type="flex" justify="end" style="margin: 10px 0 20px;">
      <Button type="primary" icon="ios-search" @click="list">查 询</Button>
    </Row>
    <Table max-height="650" :columns="columns" :data="tableData"></Table>
    <Pagination ref="page" v-bind:list="list" />
  </div>
</template>
<script>
  import Pagination from "../components/commons/pagination";
  export default {
      name: 'chapter',
      components: { Pagination },
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
                  page: _this.$refs.page.pageNum,
                  size: _this.$refs.page.pageSize
              }).then((response)=>{
                  console.log('result = ', response);
                  _this.tableData = response.data.list;
                  _this.$refs.page.total = response.data.total;
              })
          },
      }
  }
</script>