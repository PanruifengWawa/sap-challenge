webpackJsonp([4],{

/***/ 507:
/***/ (function(module, exports, __webpack_require__) {


/* styles */
__webpack_require__(965)

var Component = __webpack_require__(198)(
  /* script */
  __webpack_require__(539),
  /* template */
  __webpack_require__(947),
  /* scopeId */
  "data-v-a4181628",
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 533:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_schart_js__ = __webpack_require__(914);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_schart_js___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_schart_js__);
//
//
//
//
//
//


/* harmony default export */ __webpack_exports__["default"] = ({
    data: function () {
        return {};
    },
    props: {
        canvasId: {
            type: String,
            default: ''
        },
        width: {
            type: [String, Number],
            default: 500
        },
        height: {
            type: [String, Number],
            default: 400
        },
        type: {
            type: String,
            default: 'bar'
        },
        data: {
            type: Array,
            default: []
        },
        options: {
            type: Object,
            required: false
        }
    },
    mounted: function () {
        this.renderChart();
    },
    methods: {
        renderChart: function () {
            var self = this;
            new __WEBPACK_IMPORTED_MODULE_0_schart_js___default.a(self.canvasId, self.type, self.data, self.options);
        }
    },
    watch: {
        'data': function () {
            this.renderChart();
        },
        'options': function () {
            this.renderChart();
        },
        'type': function () {
            this.renderChart();
        },
        'width': function () {
            var self = this;
            self.$nextTick(function () {
                self.renderChart();
            });
        },
        'height': function () {
            var self = this;
            self.$nextTick(function () {
                self.renderChart();
            });
        }
    }
});

/***/ }),

/***/ 539:
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue_schart__ = __webpack_require__(930);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_vue_schart___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_0_vue_schart__);
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
    components: {
        Schart: __WEBPACK_IMPORTED_MODULE_0_vue_schart___default.a
    },
    data: () => ({
        data1: [{ name: '2012', value: 1141 }, { name: '2013', value: 1499 }, { name: '2014', value: 2260 }, { name: '2015', value: 1170 }, { name: '2016', value: 970 }, { name: '2017', value: 1450 }],
        data2: [{ name: '短袖', value: 1200 }, { name: '休闲裤', value: 1222 }, { name: '连衣裙', value: 1283 }, { name: '外套', value: 1314 }, { name: '羽绒服', value: 2314 }],
        options1: {
            title: '某商店近年营业总额',
            bgColor: '#009688',
            titleColor: '#ffffff',
            fillColor: '#e0f2f1',
            axisColor: '#ffffff',
            contentColor: '#999'
        },
        options2: {
            title: '某商店各商品年度销量',
            bgColor: '#607d8b',
            titleColor: '#ffffff',
            legendColor: '#ffffff'
        }
    })
});

/***/ }),

/***/ 647:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(87)(false);
// imports


// module
exports.push([module.i, ".schart[data-v-a4181628]{width:600px;display:inline-block}.content-title[data-v-a4181628]{clear:both;font-weight:400;line-height:50px;margin:10px 0;font-size:22px;color:#1f2f3d}", ""]);

// exports


/***/ }),

/***/ 914:
/***/ (function(module, exports, __webpack_require__) {

var __WEBPACK_AMD_DEFINE_RESULT__;/*!
 * sChart JavaScript Library v1.0.2
 * http://test.omwteam.com/sChart/ | Released under the MIT license
 * Date: 2017-07-12T18:59Z
 */
(function (root, factory) {
    if (true) {
        !(__WEBPACK_AMD_DEFINE_RESULT__ = function () {
            return factory(root);
        }.call(exports, __webpack_require__, exports, module),
				__WEBPACK_AMD_DEFINE_RESULT__ !== undefined && (module.exports = __WEBPACK_AMD_DEFINE_RESULT__));
    } else if (typeof exports === 'object') {
        module.exports = factory;
    } else {
        root.sChart = factory(root);
    }
})(this, function (root) {
    'use strict';
    (function () {
        window.requestAnimationFrame = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame;
        if (!window.requestAnimationFrame) {
            window.requestAnimationFrame = function (callback) {
                var id = window.setTimeout(callback, 1000 / 60);
                return id;
            };
        }
        if (!window.cancelAnimationFrame) {
            window.cancelAnimationFrame = function (id) {
                clearTimeout(id);
            };
        }
    }());
    /**
     * 生成图表
     * @param {String} canvas 画布元素id
     * @param {String} type 图表类型
     * @param {Array} data 生成图表的数据
     * @param {Object} options 图表参数 可选参数
     */
    function sChart(canvas, type, data, options) {
        this.canvas = document.getElementById(canvas);
        this.ctx = this.canvas.getContext('2d');
        this.type = type;
        this.data = data; // 存放图表数据
        this.dataLength = this.data.length; // 图表数据的长度
        this.width = this.canvas.width; // canvas 宽度
        this.height = this.canvas.height; // canvas 高度
        this.padding = 50; // canvas 内边距
        this.yEqual = 5; // y轴分成5等分
        this.yLength = 0; // y轴坐标点之间的真实长度
        this.xLength = 0; // x轴坐标点之间的真实长度
        this.yFictitious = 0; // y轴坐标点之间显示的间距
        this.yRatio = 0; // y轴坐标真实长度和坐标间距的比
        this.bgColor = '#ffffff'; // 默认背景颜色
        this.fillColor = '#1E9FFF'; // 默认填充颜色
        this.axisColor = '#666666'; // 坐标轴颜色
        this.contentColor = '#eeeeee'; // 内容横线颜色
        this.titleColor = '#000000'; // 图表标题颜色
        this.title = ''; // 图表标题
        this.titlePosition = 'top'; // 图表标题位置: top / bottom
        this.looped = null; // 是否循环
        this.current = 0; // 当前加载柱状图高度的百分数
        this.radius = 100; // 饼图半径和环形图外圆半径
        this.innerRadius = 70; // 环形图内圆半径
        this.colorList = ['#1E9FFF', '#13CE66', '#F7BA2A', '#FF4949', '#72f6ff', '#199475', '#e08031', '#726dd1']; // 饼图颜色列表
        this.legendColor = '#000000'; // 图例文字颜色
        this.legendTop = 40; // 图例距离顶部高度
        this.totalValue = this.getTotalValue(); // 获取饼图数据总和
        this.init(options);
    }
    sChart.prototype = {
        init: function (options) {
        	if(this.dataLength === 0){
                return false;
            }
            if (options) {
                for (var key in options) {
                    if (key === 'colorList' && Array.isArray(options[key])) {
                        this[key] = options[key].concat(this[key])
                    } else {
                        this[key] = options[key];
                    }
                }
            }
            if (this.type === 'bar' || this.type === 'line') {
                this.yLength = Math.floor((this.height - this.padding * 2 - 10) / this.yEqual);
                this.xLength = Math.floor((this.width - this.padding * 1.5 - 10) / this.dataLength);
                this.yFictitious = this.getYFictitious(this.data);
                this.yRatio = this.yLength / this.yFictitious;
                this.looping();
            } else {
                this.drawPieUpdate();
            }
        },
        /**
         * 循环生成动画效果
         */
        looping: function () {
            this.looped = window.requestAnimationFrame(this.looping.bind(this));
            if (this.current < 100) {
                this.current = (this.current + 3) > 100 ? 100 : (this.current + 3);
                this.drawAnimation();
            } else {
                window.cancelAnimationFrame(this.looped);
                this.looped = null;
            }
        },
        /**
         * 绘制动画，被循环函数调用生成动画
         */
        drawAnimation: function () {
            for (var i = 0; i < this.dataLength; i++) {
                var x = Math.ceil(this.data[i].value * this.current / 100 * this.yRatio);
                var y = this.height - this.padding - x;

                this.data[i].left = this.padding + this.xLength * (i + 0.25);
                this.data[i].top = y;
                this.data[i].right = this.padding + this.xLength * (i + 0.75);
                this.data[i].bottom = this.height - this.padding;
                this.drawBarUpdate();
            }
        },
        /**
         * 绘制完整的柱状图或折线图
         */
        drawBarUpdate: function () {
            this.ctx.fillStyle = this.bgColor;
            this.ctx.fillRect(0, 0, this.width, this.height);
            this.drawAxis();
            this.drawPoint();
            this.drawTitle();
            this.drawBarChart();
        },
        /**
         * 绘制完整的饼状图或环形图
         */
        drawPieUpdate: function () {
            this.ctx.fillStyle = this.bgColor;
            this.ctx.fillRect(0, 0, this.width, this.height);
            this.drawLegend();
            this.drawTitle();
            this.drawPieChart();
        },
        /**
         * 把数据绘制出柱状或折线
         */
        drawBarChart: function () {
            this.ctx.fillStyle = this.fillColor;
            this.ctx.strokeStyle = this.fillColor;
            for (var i = 0; i < this.dataLength; i++) {
                // 绘制折线
                if (this.type === 'line') {
                    this.ctx.beginPath();
                    this.ctx.arc(this.data[i].left + this.xLength / 4, this.data[i].top, 2, 0, 2 * Math.PI, true);
                    this.ctx.fill();
                    if (i !== this.dataLength - 1) {
                        this.ctx.moveTo(this.data[i].left + this.xLength / 4, this.data[i].top);
                        this.ctx.lineTo(this.data[i + 1].left + this.xLength / 4, this.data[i + 1].top);
                    }
                    this.ctx.stroke();
                } else if (this.type === 'bar') {
                    // 绘制柱状
                    this.ctx.fillRect(
                        this.data[i].left,
                        this.data[i].top,
                        this.data[i].right - this.data[i].left,
                        this.data[i].bottom - this.data[i].top
                    );
                }

                this.ctx.font = '12px Arial'
                this.ctx.fillText(
                    this.data[i].value * this.current / 100,
                    this.data[i].left + this.xLength / 4,
                    this.data[i].top - 5
                );
            }
        },
        /**
         * 把数据绘制出饼状或环形
         */
        drawPieChart: function () {
            var x = this.width / 2,
                y = this.height / 2,
                x1 = 0,
                y1 = 0;
            for (var i = 0; i < this.dataLength; i++) {
                this.ctx.beginPath();
                this.ctx.fillStyle = this.colorList[i];
                this.ctx.moveTo(x, y);
                this.data[i].start = i === 0 ? -Math.PI / 2 : this.data[i - 1].end;
                this.data[i].end = this.data[i].start + this.data[i].value / this.totalValue * 2 * Math.PI;
                // 绘制扇形
                this.ctx.arc(x, y, this.radius, this.data[i].start, this.data[i].end);
                this.ctx.closePath();
                this.ctx.fill();

                this.data[i].middle = (this.data[i].start + this.data[i].end) / 2;
                x1 = Math.ceil(Math.abs(this.radius * Math.cos(this.data[i].middle)));
                y1 = Math.floor(Math.abs(this.radius * Math.sin(this.data[i].middle)));

                this.ctx.strokeStyle = this.colorList[i];
                // 绘制各个扇形边上的数据
                if (this.data[i].middle <= 0) {
                    this.ctx.textAlign = 'left';
                    this.ctx.moveTo(x + x1, y - y1);
                    this.ctx.lineTo(x + x1 + 10, y - y1 - 10);
                    this.ctx.moveTo(x + x1 + 10, y - y1 - 10);
                    this.ctx.lineTo(x + x1 + this.radius / 2, y - y1 - 10);
                    this.ctx.stroke();
                    this.ctx.fillText(this.data[i].value, x + x1 + 5 + this.radius / 2, y - y1 - 5);
                } else if (this.data[i].middle > 0 && this.data[i].middle <= Math.PI / 2) {
                    this.ctx.textAlign = 'left';
                    this.ctx.moveTo(x + x1, y + y1);
                    this.ctx.lineTo(x + x1 + 10, y + y1 + 10);
                    this.ctx.moveTo(x + x1 + 10, y + y1 + 10);
                    this.ctx.lineTo(x + x1 + this.radius / 2, y + y1 + 10);
                    this.ctx.stroke();
                    this.ctx.fillText(this.data[i].value, x + x1 + 5 + this.radius / 2, y + y1 + 15);
                } else if (this.data[i].middle > Math.PI / 2 && this.data[i].middle < Math.PI) {
                    this.ctx.textAlign = 'right';
                    this.ctx.moveTo(x - x1, y + y1);
                    this.ctx.lineTo(x - x1 - 10, y + y1 + 10);
                    this.ctx.moveTo(x - x1 - 10, y + y1 + 10);
                    this.ctx.lineTo(x - x1 - this.radius / 2, y + y1 + 10);
                    this.ctx.stroke();
                    this.ctx.fillText(this.data[i].value, x - x1 - 5 - this.radius / 2, y + y1 + 15);
                } else {
                    this.ctx.textAlign = 'right';
                    this.ctx.moveTo(x - x1, y - y1);
                    this.ctx.lineTo(x - x1 - 10, y - y1 - 10);
                    this.ctx.moveTo(x - x1 - 10, y - y1 - 10);
                    this.ctx.lineTo(x - x1 - this.radius / 2, y - y1 - 10);
                    this.ctx.stroke();
                    this.ctx.fillText(this.data[i].value, x - x1 - 5 - this.radius / 2, y - y1 - 5);
                }
            }
            // 如果类型是环形图，绘制一个内圆
            if (this.type === 'ring') {
                this.ctx.beginPath();
                this.ctx.fillStyle = this.bgColor;
                this.ctx.arc(x, y, this.innerRadius, 0, Math.PI * 2);
                this.ctx.fill();
            }
        },
        /**
         * 绘制坐标轴
         */
        drawAxis: function () {
            this.ctx.beginPath();
            this.ctx.strokeStyle = this.axisColor;
            // y轴线, +0.5是为了解决canvas画1像素会显示成2像素的问题
            this.ctx.moveTo(this.padding + 0.5, this.height - this.padding + 0.5);
            this.ctx.lineTo(this.padding + 0.5, this.padding + 0.5);
            // x轴线
            this.ctx.moveTo(this.padding + 0.5, this.height - this.padding + 0.5);
            this.ctx.lineTo(this.width - this.padding / 2 + 0.5, this.height - this.padding + 0.5);
            this.ctx.stroke();
        },
        /**
         * 绘制坐标轴上的点和值
         */
        drawPoint: function () {
            // x轴坐标点
            this.ctx.beginPath();
            this.ctx.font = '12px Microsoft YaHei';
            this.ctx.textAlign = 'center';
            this.ctx.fillStyle = this.axisColor;
            for (var i = 0; i < this.dataLength; i++) {
                var name = this.data[i].name;
                var xlen = this.xLength * (i + 1);
                this.ctx.moveTo(this.padding + xlen + 0.5, this.height - this.padding + 0.5);
                this.ctx.lineTo(this.padding + xlen + 0.5, this.height - this.padding + 5.5);
                this.ctx.fillText(name, this.padding + xlen - this.xLength / 2, this.height - this.padding + 15);
            }
            this.ctx.stroke();

            // y轴坐标点
            this.ctx.beginPath();
            this.ctx.font = '12px Microsoft YaHei';
            this.ctx.textAlign = 'right';
            this.ctx.fillStyle = this.axisColor;
            this.ctx.moveTo(this.padding + 0.5, this.height - this.padding + 0.5);
            this.ctx.lineTo(this.padding - 4.5, this.height - this.padding + 0.5);
            this.ctx.fillText(0, this.padding - 10, this.height - this.padding + 5);
            for (var i = 0; i < this.yEqual; i++) {
                var y = this.yFictitious * (i + 1);
                var ylen = this.yLength * (i + 1);
                this.ctx.beginPath();
                this.ctx.strokeStyle = this.axisColor;
                this.ctx.moveTo(this.padding + 0.5, this.height - this.padding - ylen + 0.5);
                this.ctx.lineTo(this.padding - 4.5, this.height - this.padding - ylen + 0.5);
                this.ctx.stroke();
                this.ctx.fillText(y, this.padding - 10, this.height - this.padding - ylen + 5);
                this.ctx.beginPath();
                this.ctx.strokeStyle = this.contentColor;
                this.ctx.moveTo(this.padding + 0.5, this.height - this.padding - ylen + 0.5)
                this.ctx.lineTo(this.width - this.padding / 2 + 0.5, this.height - this.padding - ylen + 0.5);
                this.ctx.stroke();
            }
        },
        /**
         * 绘制图表标题
         */
        drawTitle: function () {
            if (this.title) {
                this.ctx.beginPath();
                this.ctx.textAlign = 'center';
                this.ctx.fillStyle = this.titleColor;
                this.ctx.font = '16px Microsoft YaHei';
                if (this.titlePosition === 'bottom' && this.padding >= 40) {
                    this.ctx.fillText(this.title, this.width / 2, this.height - 5)
                } else {
                    this.ctx.fillText(this.title, this.width / 2, this.padding / 2)
                }
            }
        },
        /**
         * 绘制饼状图或环形图的图例
         */
        drawLegend: function () {
            for (var i = 0; i < this.dataLength; i++) {
                this.ctx.fillStyle = this.colorList[i];
                this.ctx.fillRect(10, this.legendTop + 20 * i, 20, 11);
                this.ctx.fillStyle = this.legendColor;
                this.ctx.font = '12px Microsoft YaHei';
                this.ctx.textAlign = 'left';
                this.ctx.fillText(this.data[i].name, 35, 50 + 20 * i);
            }
        },
        /**
         * y轴坐标点之间显示的间距
         * @param data 生成图表的数据
         * @return y轴坐标间距
         */
        getYFictitious: function (data) {
            var arr = data.slice(0);
            arr.sort(function (a, b) {
                return -(a.value - b.value);
            });
            var len = Math.ceil(arr[0].value / this.yEqual);
            var pow = len.toString().length - 1;
            pow = pow > 2 ? 2 : pow;
            return Math.ceil(len / Math.pow(10, pow)) * Math.pow(10, pow);
        },
        /**
         * 获取饼状或环形图的数据总和
         * @return {Number} total
         */
        getTotalValue: function () {
            var total = 0;
            for (var i = 0; i < this.dataLength; i++) {
                total += this.data[i].value;
            }
            return total;
        }
    }
    return sChart;
});

/***/ }),

/***/ 930:
/***/ (function(module, exports, __webpack_require__) {

var Component = __webpack_require__(198)(
  /* script */
  __webpack_require__(533),
  /* template */
  __webpack_require__(938),
  /* scopeId */
  null,
  /* cssModules */
  null
)

module.exports = Component.exports


/***/ }),

/***/ 938:
/***/ (function(module, exports) {

module.exports={render:function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', [_c('canvas', {
    attrs: {
      "id": _vm.canvasId,
      "width": _vm.width,
      "height": _vm.height
    }
  })])
},staticRenderFns: []}

/***/ }),

/***/ 947:
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
  }), _vm._v(" 图表")]), _vm._v(" "), _c('el-breadcrumb-item', [_vm._v("基础图表")])], 1)], 1), _vm._v(" "), _vm._m(0), _vm._v(" "), _c('div', {
    staticClass: "schart"
  }, [_c('div', {
    staticClass: "content-title"
  }, [_vm._v("柱状图")]), _vm._v(" "), _c('schart', {
    attrs: {
      "canvasId": "bar",
      "width": "500",
      "height": "400",
      "data": _vm.data1,
      "type": "bar",
      "options": _vm.options1
    }
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "schart"
  }, [_c('div', {
    staticClass: "content-title"
  }, [_vm._v("折线图")]), _vm._v(" "), _c('schart', {
    attrs: {
      "canvasId": "line",
      "width": "500",
      "height": "400",
      "data": _vm.data1,
      "type": "line",
      "options": _vm.options1
    }
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "schart"
  }, [_c('div', {
    staticClass: "content-title"
  }, [_vm._v("饼状图")]), _vm._v(" "), _c('schart', {
    attrs: {
      "canvasId": "pie",
      "width": "500",
      "height": "400",
      "data": _vm.data2,
      "type": "pie",
      "options": _vm.options2
    }
  })], 1), _vm._v(" "), _c('div', {
    staticClass: "schart"
  }, [_c('div', {
    staticClass: "content-title"
  }, [_vm._v("环形图")]), _vm._v(" "), _c('schart', {
    attrs: {
      "canvasId": "ring",
      "width": "500",
      "height": "400",
      "data": _vm.data2,
      "type": "ring",
      "options": _vm.options2
    }
  })], 1)])
},staticRenderFns: [function (){var _vm=this;var _h=_vm.$createElement;var _c=_vm._self._c||_h;
  return _c('div', {
    staticClass: "plugins-tips"
  }, [_vm._v("\n        vue-schart：vue.js封装sChart.js的图表组件。\n        访问地址："), _c('a', {
    attrs: {
      "href": "https://github.com/lin-xin/vue-schart",
      "target": "_blank"
    }
  }, [_vm._v("vue-schart")])])
}]}

/***/ }),

/***/ 965:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(647);
if(typeof content === 'string') content = [[module.i, content, '']];
if(content.locals) module.exports = content.locals;
// add the styles to the DOM
var update = __webpack_require__(197)("2939ac9d", content, true);

/***/ })

});