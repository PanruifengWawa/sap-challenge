import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/home',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            children:[
                {
                    path: '/',
                    component: resolve => require(['../components/page/Readme.vue'], resolve)
                },
                {
                    path: '/joblist',
                    component: resolve => require(['../components/page/JobList.vue'], resolve)
                },
                {
                    path: '/addjob',
                    component: resolve => require(['../components/page/AddJob.vue'], resolve)     // vue-datasource组件
                },
                {
                    path: '/companyInfo',
                    component: resolve => require(['../components/page/CompanyInfo.vue'], resolve)
                },
                {
                    path: '/examlist',
                    component: resolve => require(['../components/page/ExamList.vue'], resolve)    // Vue-Quill-Editor组件
                },
                {
                    path: '/addexam',
                    component: resolve => require(['../components/page/AddExam.vue'], resolve)     // Vue-Quill-Editor组件
                },
                {
                    path: '/upload',
                    component: resolve => require(['../components/page/Upload.vue'], resolve)       // Vue-Core-Image-Upload组件
                },
                {
                    path: '/basecharts',
                    component: resolve => require(['../components/page/BaseCharts.vue'], resolve)   // vue-schart组件
                },
                {
                    path: '/drag',
                    component: resolve => require(['../components/page/DragList.vue'], resolve)    // 拖拽列表组件
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
    ]
})
