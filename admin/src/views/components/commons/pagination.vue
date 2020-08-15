<template>
  <Row type="flex" justify="center" style="margin-top: 20px">
    <Page :total="total" :page-size="pageSize" :page-size-opts="pageSizeOpts" show-sizer show-total show-elevator @on-change="changePageNum" @on-page-size-change="changePageSize" />
  </Row>
</template>
<script>
    export default {
        name: 'pagination',
        props: {
            list: {
                type: Function,
                default: null
            }
        },
        data() {
            return{
                pageSize: 10,
                pageNum: 1,
                total: -1,
                pageSizeOpts: [10, 20, 30, 40]
            }
        },
        methods: {
            /**
             * 改变页码号执行的方法
             * @param pageNum
             */
            changePageNum(pageNum) {
                let _this = this;
                _this.pageNum = pageNum;
                _this.list();
            },

            /**
             * 改变页大小执行的方法
             * @param pageSize
             */
            changePageSize(pageSize) {
                let _this = this;
                _this.pageSize = pageSize;
                _this.list();
            }
        }
    }
</script>