package xyz.lengmaomao.autopapersystem.beans;

import org.springframework.stereotype.Component;
import xyz.lengmaomao.autopapersystem.VO.PaperCreateRule;
import xyz.lengmaomao.autopapersystem.service.SubjectService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
//@Scope("prototype")
public class GA {
    /**
     * 变异概率
     */
    private static final double MUTATION_RATE = 0.085;
    /**
     * 精英主义
     */
    private static final boolean ELITISM = true;
    /**
     * 淘汰数组大小
     */
    private static final int TOURNAMENT_SIZE = 5;


    //注入subjectService
    @Resource
    protected SubjectService subjectService;

    private static GA ga;

    @PostConstruct
    public void init(){
        ga = this;
        ga.subjectService = this.subjectService;
    }

    /**
     * 种群进化
     */
    public static Population evolvePopulation(Population pop, PaperCreateRule rule){
        Population population = new Population();
        int elitismOffset;
        // 精英主义
        if (ELITISM) {
            //防止刷掉最优解
            elitismOffset = 1;
            // 保留上一代最优秀个体
            Paper fitness = pop.getFitness();
            population.getPapers().add(fitness);
        }
        //进行交叉操作
        for (int i=elitismOffset;i<pop.getPapers().size();i++){
            //选择优秀个体
            Paper p1 = select(pop);
            System.out.println("p1.author:"+p1.getPaperAuthor());
            Paper p2 = select(pop);
            while (p1.equals(p2)){
                p2 = select(pop);
            }
            System.out.println("p2.author:"+p2.getPaperAuthor());
//            System.out.println("p1:"+p1.getPaperId());
//            System.out.println("p2:"+p2.getPaperId());
            //交叉
            Paper child = crossover(p1,p2);
            child.makePaperScore();
            child.setKpCoverage(rule);
            child.makeAdaptationDegree(rule,Paper.KP_WEIGHT,Paper.DIFFICULTY_WEIGHT);
            child.makeDifficulty();
            child.setPaperAuthor(rule.getAuthor());
            population.getPapers().add(child);
        }
        //种群变异
        Paper paperTemp;
        for (int i = elitismOffset; i < population.getPapers().size(); i++){
            paperTemp = population.getPapers().get(i);
            System.out.println("paperTempAuthor:"+paperTemp.getPaperAuthor());
            mutate(paperTemp);
            //计算知识点覆盖率
            paperTemp.makeAdaptationDegree(rule,Paper.KP_WEIGHT,Paper.DIFFICULTY_WEIGHT);
            paperTemp.setKpCoverage(rule);
        }
        return population;
    }
    /**
     * 交叉算子
     *
     * @param p1 父亲
     * @param p2 母亲
     *
     */
    public static Paper crossover(Paper p1, Paper p2){
        //子代题目数量=父亲题目数量
        Paper child = new Paper(p1.getTotalSubjects().size());
        List<Subject> childSubjects = new ArrayList<>();
        childSubjects.addAll(p1.getTotalSubjects());
        child.setTotalSubjects(childSubjects);
        //随机两个实数(s1,s2<父亲题目数量)
        int s1 = (int)(Math.random()*p1.getTotalSubjects().size());
        int s2 = (int)(Math.random()*p1.getTotalSubjects().size());
        //定义startPos endPos之间的序列，会被遗传到下一代
        int starPoint = s1 < s2 ? s1 : s2;
        int endPoint = s1 > s2 ? s1 : s2;
        //从starPoint到endPoint
        for (int i=starPoint ; i<endPoint ;i++){
            //临时subject
            Subject subjectTemp = p2.getTotalSubjects().get(i);
            if (!child.getTotalSubjects().contains(subjectTemp))
            child.getTotalSubjects().set(i,subjectTemp);
        }
        return child;
    }

    /**
     * 突变算子
     *
     * @param paper
     *
     */
    public static void mutate(Paper paper){
        Subject subjectTemp;
        Subject subjectMutate;
        for (int i = 0; i < paper.getTotalSubjects().size(); i++){
            //当随机数小于突变率 则进行突变
            if (Math.random() < MUTATION_RATE) {
                //进行突变
                subjectTemp =paper.getTotalSubjects().get(i);
                System.out.println(subjectTemp);
                //从题库里选择一个额外的题目
                subjectMutate = ga.subjectService.getSingleSubjectByType(subjectTemp.getSubjectType(),paper.getPaperAuthor());
                //当不为同一个题目时,则进行插入
                while (subjectMutate.equals(subjectTemp)){
                    subjectMutate = ga.subjectService.getSingleSubjectByType(subjectTemp.getSubjectType(),paper.getPaperAuthor());
                }
                paper.getTotalSubjects().set(i,subjectMutate);
            }
        }
    }

    /**
     * 选择算子
     *
     * @param population
     *
     */
    public static Paper select(Population population){
        Population pop = new Population();
        List<Paper> papers = new ArrayList<>();
        for (int i = 0;i<TOURNAMENT_SIZE; i++){
            papers.add(population.getPapers().get((int) (Math.random() * population.getPapers().size())));
        }
        pop.setPapers(papers);
        return pop.getFitness();
    }
}
