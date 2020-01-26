package cn.edu.gzmu.model.dto;

import cn.edu.gzmu.model.entity.PaperQuestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author BugRui
 * @date 2020/1/20 下午4:41
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PaperInfo implements Serializable {
    /**
     * examId
     */
    private Long examId;
    /**
     * paper Id
     */
    private Long id;
    /**
     * 单选题 info
     */
    private List<QuestionInfo> singleSel;
    /**
     * 多选题info
     */
    private List<QuestionInfo> multiSel;
    /**
     * 判断题info
     */
    private List<QuestionInfo> judgement;
    /**
     * 填空题info
     */
    private List<QuestionInfo> fillBlank;
    /**
     * 简答题info
     */
    private List<QuestionInfo> essay;
    /**
     * 编程题info
     */
    private List<QuestionInfo> program;

    public List<PaperQuestion> createPaperQuestionList(Long paperId) {
        List<PaperQuestion> paperQuestionList = new ArrayList<>();
        addPaperQuestionList(paperQuestionList, singleSel, paperId);
        addPaperQuestionList(paperQuestionList, multiSel, paperId);
        addPaperQuestionList(paperQuestionList, judgement, paperId);
        addPaperQuestionList(paperQuestionList, fillBlank, paperId);
        addPaperQuestionList(paperQuestionList, essay, paperId);
        addPaperQuestionList(paperQuestionList, program, paperId);
        return paperQuestionList;
    }

    public static PaperInfo convert(Long examId, Long id, List<PaperQuestion> singleSelList, List<PaperQuestion> multiSelList,
                                    List<PaperQuestion> judgementList, List<PaperQuestion> fillBlankList,
                                    List<PaperQuestion> essayList, List<PaperQuestion> programList) {
        return new PaperInfo().setId(id)
                .setExamId(examId)
                .setSingleSel(QuestionInfo.convert(singleSelList))
                .setMultiSel(QuestionInfo.convert(multiSelList))
                .setJudgement(QuestionInfo.convert(judgementList))
                .setFillBlank(QuestionInfo.convert(fillBlankList))
                .setEssay(QuestionInfo.convert(essayList))
                .setProgram(QuestionInfo.convert(programList));

    }

    public static PaperInfo convert(Long examId, List<QuestionInfo> singleSel, List<QuestionInfo> multiSel,
                                    List<QuestionInfo> judgement, List<QuestionInfo> fillBlank,
                                    List<QuestionInfo> essay, List<QuestionInfo> program) {
        return new PaperInfo()
                .setExamId(examId)
                .setSingleSel(singleSel)
                .setMultiSel(multiSel)
                .setJudgement(judgement)
                .setFillBlank(fillBlank)
                .setEssay(essay)
                .setProgram(program);

    }

    public void clearAnswer() {
        //清除答案
        singleSel.forEach(item -> {
            item.getQuestionDetail().clearAnswer();
        });
        multiSel.forEach(item -> {
            item.getQuestionDetail().clearAnswer();
        });
        judgement.forEach(item -> {
            item.getQuestionDetail().clearAnswer();
        });
        fillBlank.forEach(item -> {
            item.getQuestionDetail().clearAnswer();
        });
        essay.forEach(item -> {
            item.getQuestionDetail().clearAnswer();
        });
        program.forEach(item -> {
            item.getQuestionDetail().clearAnswer();
        });
    }


    private void addPaperQuestionList(List<PaperQuestion> paperQuestionList, List<QuestionInfo> questionInfoList, Long paperId) {
        questionInfoList.forEach(questionInfo -> {
            paperQuestionList.add(questionInfo.modelMapperPaperQuestion(paperId));
        });
    }
}
