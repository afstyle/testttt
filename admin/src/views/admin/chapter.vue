<template>
  <div>
    <h1>大章页面</h1>
  </div>
</template>
<script>
  export default {
      name: 'chapter',
      mounted() {
          let _this = this;
          _this.list();
      },
      methods: {
          list() {
            let _this = this;
            _this.$ajax.get('http://127.0.0.1:9002/business/admin/chapter/list').then((response)=>{
                console.log('result = ', response);
            })
          },
      }
  }
</script>