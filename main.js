          var recorder;  
            var audio = document.querySelector('audio');  
              
            function startRecording() {  
               if(recorder){
                   recorder.start();
                   return;
               }
               
                HZRecorder.get(function (rec) {  
                    recorder = rec;  
                    recorder.start();  
                },{error:showError});  
            }  
              
              
            function obtainRecord(){  
                 if(!recorder){
                    showError("请先录音");
                    return;
                }
               var record = recorder.getBlob();  
               if(record.duration!==0){
               downloadRecord(record.blob);
               }else{
                   showError("请先录音")
               }
               recorder.clear();
            };  

            function downloadRecord(record){
              // var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a')
              //   save_link.href = URL.createObjectURL(record);
              //   var now=new Date;
              //   save_link.download = now.Format("yyyyMMddhhmmss");
              //   fake_click(save_link);

                var formdata = new FormData();
                formdata.append('data',record);
                console.log(formdata);
                $.ajax({
    url: 'http://localhost:8080/api/test/upload',
    type: 'POST',
    cache: false,
    data: formdata,
    processData: false,
    contentType: false
}).done(function(res) {
console.log(res);
var x = JSON.parse(res);
alert(x.data.result)


}).fail(function(res) {}); 
            }

       
            function fake_click(obj) {
            var ev = document.createEvent('MouseEvents');
            ev.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
            obj.dispatchEvent(ev);
            }

                 function getStr(){
              var now=new Date;
              var str= now.toDateString();
            }

            function stopRecord(){  
                recorder&&recorder.stop();  
            };  
            var msg={};
            //发送音频片段
            var msgId=1;
            function send(){
                if(!recorder){
                    showError("请先录音");
                    return;
                }

               var data=recorder.getBlob();
               if(data.duration==0){
                     showError("请先录音");
                    return;
               }
//                msg[msgId]=data;
                recorder.clear();
                console.log(data);
                
                
                var formdata = new FormData();
                formdata.append('data',data.blob);
                console.log(formdata);
                $.ajax({
               		url: 'http://localhost:8081/api/voice/upload',
               		type: 'POST',
               		cache: false,
               		data: formdata,
               		processData: false,
               		contentType: false
                }).done(function(res) {
                	console.log(res);
                	var result = res.data;
                	var content = "";
                	console.log(company);
                	switch(result) {
                		case 'AT':
                			content = "地址：" + company.address + "<br/>电话：" + company.phone;
                			break;
                		case 'CI':
                			content = "公司介绍：" + company.description ;
                			break;
                		case 'PI':
                			for (i in position) {  
                				content += "<tr>" +  
                                "<td>" + position[i].position + ":</td>" +  
                                "<td>" + position[i].description + "</td>"  +  
                                "</tr>";  
                            }
                			content = "<table>" + content + "</table>";
                			break;
                		case 'WE':
                			content = "<a href='/startExam'>开始考试</a>"
                			break;
                		default:
                			content = "未能识别";
                	}
                	layer.open({
                		  type: 1,
                		  title: false,
                		  closeBtn: 0,
                		  area: ['320px', '195px'],
                		  shadeClose: true,
                		  skin: 'yourclass',
                		  content: content
                		});
                }).fail(function(res) {}); 
//                var dur=data.duration/10;
//                 var str="<div class='warper'><div id="+msgId+" class='voiceItem'>"+dur+"s</div></div>"
//                $(".messages").append(str);
//                msgId++;
            }
            
            $(document).on("click",".voiceItem",function(){
                var id=$(this)[0].id;
                var data=msg[id];
                playRecord(data.blob);
            })

            var ct;
            function showError(msg){
                $(".error").html(msg);
                clearTimeout(ct);
                ct=setTimeout(function() {
                    $(".error").html("")
                }, 3000);
            }


            function playRecord(blob){  
                if(!recorder){
                    showError("请先录音");
                    return;
                }
                 recorder.play(audio,blob);  
            };  
              
            /* 视频 */  
            function scamera() {  
                var videoElement = document.getElementById('video1');  
                var canvasObj = document.getElementById('canvas1');  
                var context1 = canvasObj.getContext('2d');  
                context1.fillStyle = "#ffffff";  
                context1.fillRect(0, 0, 320, 240);  
                context1.drawImage(videoElement, 0, 0, 320, 240);  
            };  
              
            function playVideo(){  
                var videoElement1 = document.getElementById('video1');  
                var videoElement2 = document.getElementById('video2');  
                videoElement2.setAttribute("src", videoElement1);  
            };  