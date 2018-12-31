require('./index.css');
require('../../util/mm.js');
// $('.btn').click(function(){ 
// //取得title和content的值 
// var title=$('#comm_title').val(); 
// var content=$('#content').val();
//  var url="{:U('Content/comment')}"; 
//  if(title==''||content=='')
//  { alert("评论或标题为空！无法提交！");
//   return false; } var art_id="{$artInfo.art_id}";
//   $.get(url,
//   	{'art_id':art_id,
//   	'comm_title':title,
//   	'comm_content':content},
//   	function(data){
//    //将接受到到的静态模板放到评论区块下面 
//    $('#comment').after(data);
//     //将评论区域内容清空 
//     $('#comm_title').val('');
//      $('#content').val('');
//      //刷新
//       location.reload(); }) 
// })
//  function comment(){ 
//  	$data=I('get.');
//   //添加入库时间
//    $data['comm_time']=time();
//     //数据入库 
//     $res=M('comm')->add($data); 
//     if($res){
//     //如果添加成功则响应一块静态模板给前端
//      $this->assign('data',$data);
//       $this->display();
//        }
//        else
//        	{
//        	 echo '评论发布失败！';
//        	 } 
