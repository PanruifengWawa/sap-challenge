webpackJsonp([9],{

/***/ 508:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(198)(
  /* script */
  __webpack_require__(540),
  /* template */
  __webpack_require__(949),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 540:
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
            company: {
                name: '',
                phone: '',
                address: '',
                description: ''
            }
        };
    },
    methods: {
        onSubmit() {
            const self = this;
            let formData = new FormData();
            for (let key in self.company) {
                if (self.company[key] != null && self.company[key] != '') {
                    formData.append(key, self.company[key]);
                }
            }
            self.updateCompanyInfo(formData);
        },
        updateCompanyInfo(companyInfo) {
            const self = this;
            self.$axios.post(self.baseUrl + "/api/company", companyInfo, {
                headers: {
                    //          	    	    	    'Content-Type': 'multipart/form-data ',
                    "token": localStorage.getItem('token')
                }
            }).then(function (response) {
                let status = response.data.status;
                if (status == 0) {
                    self.$message.success('提交成功！');
                } else {
                    self.$message.info('提交失败！');
                }
            }).catch(function (err) {
                self.$message.info('服务器错误！');
            });
        },
        getCompanyInfo() {
            const self = this;
            self.$axios.get(self.baseUrl + "/api/company", {
                headers: {
                    "token": localStorage.getItem('token')
                }
            }).then(function (response) {
                let status = response.data.status;
                if (status == 0) {
                    self.company = response.data.data;
                } else {
                    localStorage.removeItem('ms_username');
                    localStorage.removeItem('token');
                    self.$message.info('用户未登录！');
                    self.$router.push('/login');
                }
            }).catch(function (err) {
                self.$message.info('服务器错误！');
            });
        }
    },
    mounted: function () {
        this.getCompanyInfo();
    }
});

/***/ }),

/***/ 949:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('div', {
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_c('i', {
    staticClass: "el-icon-date"
  }), _vm._v(" 公司信息")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("公司信息维护")])], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "form-box"
  }, [_c('el-form', {
    ref: "form",
    attrs: {
      "model": _vm.company,
      "label-width": "80px"
    }
  }, [_c('el-form-item', {
    attrs: {
      "label": "公司名称"
    }
  }, [_c('el-input', {
    model: {
      value: (_vm.company.name),
      callback: function($$v) {
        _vm.$set(_vm.company, "name", $$v)
      },
      expression: "company.name"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "公司地址"
    }
  }, [_c('el-input', {
    model: {
      value: (_vm.company.address),
      callback: function($$v) {
        _vm.$set(_vm.company, "address", $$v)
      },
      expression: "company.address"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "公司电话"
    }
  }, [_c('el-input', {
    model: {
      value: (_vm.company.phone),
      callback: function($$v) {
        _vm.$set(_vm.company, "phone", $$v)
      },
      expression: "company.phone"
    }
  })], 1), _vm._v(" "), _c('el-form-item', {
    attrs: {
      "label": "公司简介"
    }
  }, [_c('el-input', {
    attrs: {
      "type": "textarea"
    },
    model: {
      value: (_vm.company.description),
      callback: function($$v) {
        _vm.$set(_vm.company, "description", $$v)
      },
      expression: "company.description"
    }
  })], 1), _vm._v(" "), _c('el-form-item', [_c('el-button', {
    attrs: {
      "type": "primary"
    },
    on: {
      "click": _vm.onSubmit
    }
  }, [_vm._v("提交")]), _vm._v(" "), _c('el-button', [_vm._v("取消")])], 1)], 1)], 1)])
},staticRenderFns: []}

/***/ })

});