package com.project.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
 
import com.project.model.Banners;
import com.project.model.Campaigns;
 
@Configuration
@EnableBatchProcessing
public class BatchConfig 
{
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
     
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
 
    @Bean
    public Job readCSVFilesJob() {
        return jobBuilderFactory
                .get("readCSVFilesJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .start(step2())
                .build();
    }
 
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<Campaigns, Campaigns>chunk(5)
                .reader(campaignReader())
                .writer(campaignwriter())
                .build();
    }
    
    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2").<Banners, Banners>chunk(5)
                .reader(BannerReader())
                .writer(bannerwriter())
                .build();
    }
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FlatFileItemReader<Campaigns> campaignReader() 
    {
        //Create reader instance
        FlatFileItemReader<Campaigns> campaignreader = new FlatFileItemReader<Campaigns>();
         
        //Set input file location
        campaignreader.setResource(new FileSystemResource("input/campaign.csv"));
         
        //Set number of lines to skips. Use it if file has header rows.
        campaignreader.setLinesToSkip(1);   
         
        //Configure how each line will be parsed and mapped to different values
        campaignreader.setLineMapper(new DefaultLineMapper() {
            {
                //3 columns in each row
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "cid", "name", "start_date","end_date" });
                    }
                });
                //Set values in Employee class
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Campaigns>() {
                    {
                        setTargetType(Campaigns.class);
                    }
                });
            }
        });
        return campaignreader;
    }
    
   
     
    @Bean
    public ConsoleItemWriter<Campaigns> campaignwriter() 
    {
        return new ConsoleItemWriter<Campaigns>();
    }
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public FlatFileItemReader<Banners> BannerReader() 
    {
        //Create reader instance
        FlatFileItemReader<Banners> bannerreader = new FlatFileItemReader<Banners>();
         
        //Set input file location
        bannerreader.setResource(new FileSystemResource("input/campaign.csv"));
         
        //Set number of lines to skips. Use it if file has header rows.
        bannerreader.setLinesToSkip(1);   
         
        //Configure how each line will be parsed and mapped to different values
        bannerreader.setLineMapper(new DefaultLineMapper() {
            {
                //3 columns in each row
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[] { "bid","cid", "image_url" });
                    }
                });
                //Set values in Employee class
                setFieldSetMapper(new BeanWrapperFieldSetMapper<Banners>() {
                    {
                        setTargetType(Banners.class);
                    }
                });
            }
        });
        return bannerreader;
    }
    
    @Bean
    public ConsoleItemWriter<Banners> bannerwriter() 
    {
        return new ConsoleItemWriter<Banners>();
    }
}
