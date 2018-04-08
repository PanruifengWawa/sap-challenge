webpackJsonp([6],{

/***/ 511:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(968)

var Component = __webpack_require__(198)(
  /* script */
  __webpack_require__(543),
  /* template */
  __webpack_require__(951),
  /* scopeId */
  "data-v-ce77841a",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 543:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

/* harmony default export */ __webpack_exports__["default"] = ({
    data: function () {
        return {
            ruleForm: {
                userName: '',
                password: ''
            },
            tips: "",
            rules: {
                username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
                password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
            }
        };
    },
    methods: {
        submitForm(formName) {
            const self = this;
            self.$refs[formName].validate(valid => {
                if (valid) {
                    self.login();
                    //                      localStorage.setItem('ms_username',self.ruleForm.username);
                    //                      self.tips = "aaa";
                    //                      console.log(self.tips);
                    //                      self.$router.push('/readme');
                } else {
                    tips = "请输入用户名密码";
                    return false;
                }
            });
        },
        login() {
            const self = this;
            let formData = new FormData();
            formData.append("userName", this.ruleForm.userName);
            formData.append("password", this.ruleForm.password);
            this.$axios.post(self.baseUrl + "/api/company/login", formData, {
                headers: {
                    'Content-Type': 'multipart/form-data '
                }
            }).then(function (response) {
                let status = response.data.status;
                if (status != 0) {
                    self.tips = response.data.data;
                } else {
                    localStorage.setItem('token', response.data.data);
                    localStorage.setItem('ms_username', self.ruleForm.userName);
                    self.$router.push('/home');
                }
            }).catch(function (err) {
                self.tips = "服务器错误";
            });
        }
    }
});

/***/ }),

/***/ 650:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(87)(false);
// imports


// module
exports.push([module.i, ".login-wrap[data-v-ce77841a]{position:relative;width:100%;height:100%}.ms-title[data-v-ce77841a]{position:absolute;top:50%;width:100%;margin-top:-230px;text-align:center;font-size:30px;color:#fff}.ms-login[data-v-ce77841a]{position:absolute;left:50%;top:50%;width:300px;height:160px;margin:-150px 0 0 -190px;padding:40px;border-radius:5px;background:#fff}.login-btn[data-v-ce77841a]{text-align:center}.login-btn button[data-v-ce77841a]{width:100%;height:36px}", ""]);

// exports


/***/ }),

/***/ 951:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "login-wrap"
  }, [_c('div', {
    staticClass: "ms-title"
  }, [_vm._v("后台管理系统")]), _vm._v(" "), _c('div', {
    staticClass: "ms-login"
  }, [_c('el-form', {
    ref: "ruleForm",
    staticClass: "demo-ruleForm",
    attrs: {
      "model": _vm.ruleForm,
      "rules": _vm.rules,
      "label-width": "0px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "prop": "userName"
    }
  }, [_c('el-input', {
    attrs: {
      "placeholder": "userName"
    },
    model: {
      value: (_vm.ruleForm.userName),
      callback: function($$v) {
        _vm.$set(_vm.ruleForm, "userName", $$v)
      },
      expression: "ruleForm.userName"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "prop": "password"
    }
  }, [_c('el-input', {
    attrs: {
      "type": "password",
      "placeholder": "password"
    },
    nativeOn: {
      "keyup": function($event) {
        if (!('button' in $event) && _vm._k($event.keyCode, "enter", 13, $event.key, "Enter")) { return null; }
        _vm.submitForm('ruleForm')
      }
    },
    model: {
      value: (_vm.ruleForm.password),
      callback: function($$v) {
        _vm.$set(_vm.ruleForm, "password", $$v)
      },
      expression: "ruleForm.password"
    }
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "login-btn"
  }, [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": function($event) {
        _vm.submitForm('ruleForm')
      }
    }
  }, [_vm._v("登录")])], 1), _vm._v(" "), _c('p', {
    staticStyle: {
      "font-size": "12px",
      "line-height": "30px",
      "color": "#ff4949"
    }
  }, [_vm._v(_vm._s(_vm.tips))])], 1)], 1)])
},staticRenderFns: []}

/***/ }),

/***/ 968:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(650);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(197)("790c22c2", content, true);

/***/ })

});