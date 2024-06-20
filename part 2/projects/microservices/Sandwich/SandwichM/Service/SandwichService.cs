using Sandwich.Contracts;
using Sandwich.Entities.DTO;
using Sandwich.Entities.Entity;
using Sandwich.Mappers;

namespace Sandwich.Service
{

    public interface ISandwichService
    {
        public SandwichDTO createsandwich(SandwichDTO sandwichDTO);
        IQueryable<SandwichDTO> getSandwiches();
        SandwichDTO getSandwichById(int sandwichId);
    }
    
    
    public class SandwichService
    {
        private readonly ISandwichRepository sandwichRepository;

         public SandwichService(ISandwichRepository sandwichRepository)
         {
             this.sandwichRepository = sandwichRepository;
         }

        public SandwichDTO createQuestion(SandwichDTO sandwichDTO)
        {
            Sandwiches s = SandwichMapper.ToDomain(sandwichDTO);
            Sandwiches answer = sandwichRepository.save(s);
            return SandwichMapper.ToDTO(answer);
        }

        public IQueryable<SandwichDTO> getSandwiches()
        {
            IQueryable<Sandwiches> sandwiches = sandwichRepository.findAll();
            return SandwichMapper.ToDTOList(sandwiches);
        }
    }
}