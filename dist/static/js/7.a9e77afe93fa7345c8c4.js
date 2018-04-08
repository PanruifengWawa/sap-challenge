webpackJsonp([7],{

/***/ 510:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(957)

var Component = __webpack_require__(198)(
  /* script */
  __webpack_require__(542),
  /* template */
  __webpack_require__(937),
  /* scopeId */
  "data-v-120dfb05",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 542:
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

/* harmony default export */ __webpack_exports__["default"] = ({
    data() {
        return {
            tableData: [],
            cur_page: 1,
            total: 100,
            select_word: '',
            del_list: []
        };
    },
    created() {
        this.getData();
    },
    computed: {
        data() {
            const self = this;
            return self.tableData.filter(function (d) {
                if (d.name.indexOf(self.select_word) > -1) {
                    return d;
                }
            });
        }
    },
    methods: {
        handleCurrentChange(val) {
            this.cur_page = val;
            this.getData();
        },
        getData() {
            let self = this;
            self.$axios.post('/ms/table/list', { page: self.cur_page }).then(res => {
                self.tableData = res.data.list;
            });
        },
        handleDelete(index, row) {
            this.$message.error('删除第' + (index + 1) + '行');
        }
    }
});

/***/ }),

/***/ 639:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(87)(false);
// imports


// module
exports.push([module.i, ".handle-box[data-v-120dfb05]{margin-bottom:20px}.handle-select[data-v-120dfb05]{width:120px}.handle-input[data-v-120dfb05]{width:300px;display:inline-block}", ""]);

// exports


/***/ }),

/***/ 937:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "table"
  }, [_c('div', {
    staticClass: "crumbs"
  }, [_c('el-breadcrumb', {
    attrs: {
      "separator": "/"
    }
  }, [_c('el-breadcrumb-item', [_c('i', {
    staticClass: "el-icon-menu"
  }), _vm._v(" 职位信息")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("职位列表")])], 1)], 1), _vm._v(" "), _c('div', {
    staticClass: "handle-box"
  }, [_c('el-input', {
    staticClass: "handle-input mr10",
    attrs: {
      "placeholder": "筛选职位"
    },
    model: {
      value: (_vm.select_word),
      callback: function($$v) {
        _vm.select_word = $$v
      },
      expression: "select_word"
    }
  })], 1), _vm._v(" "), _c('el-table', {
    ref: "multipleTable",
    staticStyle: {
      "width": "100%"
    },
    attrs: {
      "data": _vm.data,
      "border": ""
    }
  }, [_c('el-table-column', {
    attrs: {
      "prop": "id",
      "label": "id",
      "sortable": "",
      "width": "150"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "position",
      "label": "职位",
      "width": "120"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "prop": "name",
      "label": "职位描述"
    }
  }), _vm._v(" "), _c('el-table-column', {
    attrs: {
      "label": "操作",
      "width": "180"
    },
    scopedSlots: _vm._u([{
      key: "default",
      fn: function(scope) {
        return [_c('el-button', {
          attrs: {
            "size": "small",
            "type": "danger"
          },
          on: {
            "click": function($event) {
              _vm.handleDelete(scope.$index, scope.row)
            }
          }
        }, [_vm._v("删除")])]
      }
    }])
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "pagination"
  }, [_c('el-pagination', {
    attrs: {
      "layout": "prev, pager, next",
      "total": _vm.total
    },
    on: {
      "current-change": _vm.handleCurrentChange
    }
  })], 1)], 1)
},staticRenderFns: []}

/***/ }),

/***/ 957:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(639);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(197)("78b2ff85", content, true);

/***/ })

});