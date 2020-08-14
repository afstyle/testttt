<template>
    <div class="index">
        <Row type="flex" justify="center" align="middle">
            <Col span="24">
                <h1>
                    <img src="../../images/logo.png">
                </h1>
                <h2>
                    <div class="demo-login">
                        <Login @on-submit="handleSubmit">
                            <Mobile name="mobile" />
                            <Captcha name="captcha" :field="['mobile']" @on-get-captcha="handleGetCaptcha" />
                            <Submit />
                        </Login>
                    </div>
                </h2>
            </Col>
        </Row>
    </div>
    
</template>
<script>
    export default {
        methods: {
            handleSubmit (valid, { mobile, captcha }) {
                if (valid) {
                    this.$router.push('/admin/welcome');
                    /*this.$Modal.info({
                        title: '输入的内容如下：',
                        content: 'mobile: ' + mobile + ' | captcha: ' + captcha
                    });*/
                } else {
                    this.$router.push('/admin/welcome');
                }
            },
            handleGetCaptcha () {
                this.$Message.info('获取验证码');
            }
        }
    }
</script>
<style scoped lang="less">
    .demo-login{
        width: 400px;
        margin: 0 auto;
    }
    .index{
        width: 100%;
        position: absolute;
        top: -20%;
        bottom: 0;
        left: 0;
        text-align: center;
        h1{
            height: 100px;
            margin-bottom: 50px;
            img{
                height: 100%;
            }
        }
        .ivu-row-flex{
            height: 100%;
        }
    }
</style>