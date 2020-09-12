<template>
  <div>
    <Row type="flex" justify="end" style="margin: 10px 0 20px;">
      <span style="margin-right: auto">
        <Button type="primary" icon="md-add" @click="form = {};formModal = true;">新增</Button>
        <Button type="primary" icon="md-trash" @click="list">批量删除</Button>
      </span>
      <Button type="primary" icon="md-search" @click="list">查询</Button>
    </Row>
    <Table max-height="650" :columns="columns" :data="tableData" :loading="loading">
      <template slot-scope="{ row, index }" slot="action">
        <Button type="primary" size="small" @click="get(row.id)" style="margin-right: 5px">修 改</Button>
        <Button type="error" size="small" @click="deletes(row.id)">删 除</Button>
      </template><#list fieldList as field><#if field.enums>
      <template slot-scope="{ row, index }" slot="${field.nameHump}">
        {{options.${field.nameHump} | optionTurn(row.${field.nameHump})}}
      </template></#if></#list>
    </Table>
    <Pagination ref="page" v-bind:list="list" />

    <!-- 新增修改modal -->
    <Modal v-model="formModal" :mask-closable="false" :loading="modalLoading" :ok-text="form && form.id ? '保存' : '新增'" :title="form && form.id ? '修改' : '新增'" @on-ok="formValidate">
      <Form ref="form" :model="form" :rules="rule" :label-width="80"><#list fieldList as field><#if field.nameHump != "id" && field.nameHump != "createdAt" && field.nameHump != "updatedAt">
        <FormItem label="${field.nameCn}" prop="${field.nameHump}">
          <#if field.enums>
          <Select v-model="form.${field.nameHump}">
            <Option v-for="item in options.${field.nameHump}" :value="item.value" :key="item.value">{{ item.label }}</Option>
          </Select>
          <#else>
          <Input v-model="form.${field.nameHump}" placeholder="请输入..." />
          </#if>
        </FormItem></#if></#list>
      </Form>
    </Modal>
  </div>
</template>
<script>
  import Pagination from "../components/commons/pagination";

  export default {
    name: '${module}-${domain}',
    components: { Pagination },
    mounted() {
      let _this = this;
      _this.list();
    },
    data() {
      return{
        // loading
        loading: false,
        modalLoading: true,

        // table
        columns: [
          <#list fieldList as field>
          <#if field.nameHump != 'createdAt' && field.nameHump != 'updatedAt'>
          {
            title: '${field.nameCn}',
            <#if field.enums>
            slot: '${field.nameHump}'
            <#else>
            key: '${field.nameHump}'
            </#if>
          },
          </#if>
          </#list>
          {
            title: '操作',
            slot: 'action',
            width: 150,
            align: 'center'
          }
        ],
        tableData: [],

        // modal
        formModal: false,

        // form
        form: {},
        rule: {
          <#list fieldList as field>
          <#if field.nameHump != 'id' && field.nameHump != 'createdAt' && field.nameHump != 'updatedAt' && field.nameHump != 'sort'>
            <#if !field.nullAble || (field.length > 0)>
          ${field.nameHump}: [<#if !field.nullAble>
            { required: true, message: '${field.nameCn}不能为空', trigger: 'blur' },</#if><#if (field.length > 0)>
            { type: 'string', min: 1, max: ${field.length?c}, message: '填写长度在1~${field.length}位', trigger: 'blur' }</#if>
          ],
            </#if>
          </#if>
          </#list>
        },
        options: {
          <#list fieldList as field>
          <#if field.enums>
          ${field.nameHump}: ${field.enumsConst},
          </#if>
          </#list>
        },
      }
    },
    methods: {
      /**
       * 查询
       */
      list() {
        let _this = this;
        _this.loading = true;
        _this.Http.post('${module}/admin/${domain}/list${Domain}', {
          page: _this.$refs.page.pageNum,
          size: _this.$refs.page.pageSize
        }).then((res)=>{
          _this.loading = false;
          let result = res.result;
          _this.tableData = result.list;
          _this.$refs.page.total = result.total;
        })
      },

      /**
       * 表单验证
       */
      formValidate() {
        let _this = this;
        _this.$refs['form'].validate((valid) => {
          if (valid) {
            _this.save();
          } else {
            _this.modalSubmitFail(_this);
          }
        })
      },

      /**
       * 保存
       */
      save() {
        let _this = this;
        _this.Http.post('${module}/admin/${domain}/save${Domain}', _this.form).then((res)=>{
          if (res.success) {
            _this.$Message.success(res.message);
            _this.formModal = false;
            _this.list();
          } else {
            _this.modalSubmitFail(_this, res.message);
          }

        });
      },

      /**
       * 获取
       * @param id
       */
      get(id) {
        let _this = this;
        _this.formModal = true;
        _this.Http.get('${module}/admin/${domain}/get${Domain}/' + id).then((res)=>{
          _this.form = res.result;
        })
      },

      /**
       * 删除
       * @param id
       */
      deletes(id) {
        let _this = this;
        _this.$Modal.confirm({
          title: '确认',
          content: '<p>是否确认删除此项</p>',
          onOk: () => {
            _this.Http.delete('${module}/admin/${domain}/delete${Domain}/' + id).then((res)=>{
              if (res.success) {
                _this.$Message.success(res.message);
                _this.list();
              } else {
                _this.$Message.error(res.message);
              }
            })
          }
        });
      },

      /**
       * 失败
       * @param t
       * @param msg
       */
      modalSubmitFail(t, msg) {
        t.$Message.error(msg || '表单验证失败!');
        t.modalLoading = false;
        t.$nextTick(() => {
          t.modalLoading = true;
        })
      },
    }
  }
</script>