package com.example.memo.controller;

import com.example.memo.dto.MemoRequestDto;
import com.example.memo.dto.MemoResponseDto;
import com.example.memo.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/memos") // Prefix
public class MemoController {

    private final MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @PostMapping
    public ResponseEntity<MemoResponseDto> createMemo(@RequestBody MemoRequestDto dto){

        // Service Layer 호출, 응답
        return new ResponseEntity<>(memoService.saveMemo(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MemoResponseDto>> findAllMemos() {

        return new ResponseEntity<>(memoService.findAllMemos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id){

        return new ResponseEntity<>(memoService.findMemoById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto dto){

        return new ResponseEntity<>(memoService.updateMemo(id, dto.getTitle(), dto.getContents()), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponseDto> updateTitle(
            @PathVariable Long id,
            @RequestBody MemoRequestDto dto
    ){
        return new ResponseEntity<>(memoService.updateTitle(id, dto.getTitle(), dto.getContents()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id){

        memoService.deleteMemo(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping
//    public List<MemoResponseDto> findAllMemos() {
//        // init List
//        List<MemoResponseDto> responseList = new ArrayList<>();
//
//        // HashMap<Memo> -> List<MemoResponseDto>
//        for (Memo memo : memoList.values()){
//            MemoResponseDto responseDto = new MemoResponseDto(memo);
//            responseList.add(responseDto);
//        }
//
//        // Map to List
////        responseList = memoList.values().stream().map(MemoResponseDto::new).toList();
//
//        return responseList;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<MemoResponseDto> findMemoById(@PathVariable Long id){
//
//        Memo memo = memoList.get(id);
//
//        if(memo == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<MemoResponseDto> updateMemoById(
//            @PathVariable Long id,
//            @RequestBody MemoRequestDto dto
//    ) {
//        Memo memo = memoList.get(id);
//
//        if(memo == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        if (dto.getTitle() == null || dto.getContents() == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        memo.update(dto);
//
//        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
//    }
//
//    @PatchMapping(path = "/{id}", consumes = "application/json")
//    public ResponseEntity<MemoResponseDto> updateTitle(
//            @PathVariable Long id,
//            @RequestBody MemoRequestDto dto
//    ) {
//        Memo memo = memoList.get(id);
//
//        // NPE 방지
//        if(memo == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        if (dto.getTitle() == null || dto.getContents() != null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        memo.updateTitle(dto);
//
//        return new ResponseEntity<>(new MemoResponseDto(memo), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteMemo(@PathVariable Long id) {
//        // memoList 의 Key 값에 id 를 포함하고 있다면
//        if(memoList.containsKey(id)){
//            memoList.remove(id);
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
